package pd2.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pd2.model.Spele;
import pd2.model.komandas.Komanda;
import pd2.model.mainas.Maina;
import pd2.model.rest.Goalkeeper;
import pd2.model.sodi.Sods;
import pd2.model.speletaji.Speletajs;
import pd2.model.varti.VG;
import pd2.utils.Utilities;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PlayersService {

    @Autowired
    Firestore database;
    @Autowired
    GoalkeeperService goalkeeperService;

    public Speletajs getSpeletajs(String ref) {
        DocumentReference docRef = database.collection("Players").document(ref);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Speletajs speletajs = null;
        if (document.exists()) {
            speletajs = document.toObject(Speletajs.class);
            System.out.println(speletajs);
        } else {
            System.out.println("Creation of new player: " + ref);
        }
        return speletajs;
    }

    public void saveSpeletajs(Spele spele) {
        for (Komanda komanda : spele.getKomanda()) {
            for (Speletajs speletajs : komanda.getSpeletaji().getSpeletaji()) {
                // database player id
                String id = komanda.getNosaukums() + " " + speletajs.getNr();
                Speletajs fromDb = getSpeletajs(id);
                if (fromDb != null) speletajs = fromDb;
                speletajs.setKomanda(komanda.getNosaukums());
                if (komanda.getPamatsastavs().getSpeletajs().contains(speletajs)) {
                    speletajs.setPamatsastavs("Y");
                } else {
                    speletajs.setPamatsastavs("N");
                }

                if (komanda.getSodi() != null)
                    for (Sods sods : komanda.getSodi().getSodi()) {
                        if (speletajs.getNr() == sods.getNr()) {
                            speletajs.setSodi(1);
                            if ((speletajs.getSodi() % 2) == 1)
                                speletajs.setYellow(1);
                            else {
                                speletajs.setRed(1);
                                speletajs.setTotal(Utilities.fullTime(sods.getLaiks()).getTime());
                            }
                        }
                    }

                if (komanda.getMainas() != null) {
                    for (Maina maina : komanda.getMainas().getMaina()) {
                        if (speletajs.getNr() == maina.getNr1()) {
                            speletajs.setMainas(1);
                            speletajs.setTotal(Utilities.fullTime(maina.getLaiks()).getTime());

                        } else if (speletajs.getNr() == maina.getNr2()) {
                            speletajs.setMainas(1);
                            speletajs.setTotal(Utilities.fullTime(maina.getLaiks()).getTime());
                        }
                    }
                } else {
                    speletajs.setTotal(Utilities.fullTime("60:00").getTime());
                }

                if (komanda.getVarti() != null)
                    for (VG vg : komanda.getVarti().getVarti()) {
                        if (speletajs.getNr() == vg.getNr() && !speletajs.getLoma().equals("V")) speletajs.setVg(1);
                        if (vg.getP() != null)
                            for (Speletajs p : vg.getP()) {
                                if (speletajs.getNr() == p.getNr()) speletajs.setP(1);
                            }
                    }
                database.collection("Players").document(speletajs.getKomanda() + " " + speletajs.getNr()).set(speletajs);
                if (speletajs.getLoma().equals("V")) {
                    System.out.println("WE ARE HERE");
                    for (Komanda win : spele.getKomanda()) {
                        if (win.getNosaukums() != speletajs.getKomanda()) goalkeeperService.saveGoalkeeper(speletajs, win);
                    }
                }
            }
        }
    }

    public List<Speletajs> getSpeletaji() {
        List<Speletajs> players = new ArrayList<>();
        ApiFuture<QuerySnapshot> future =
                database.collection("Players").get();
        List<DocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (DocumentSnapshot document : documents) {
            players.add(document.toObject(Speletajs.class));
        }
        // sort with comparator
        Utilities.top(players);
        return players.subList(0,10);
    }

    public List<String> getPlayers() {
        List<String> players = new ArrayList<>();
        ApiFuture<QuerySnapshot> future =
                database.collection("Players").get();
        List<DocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (DocumentSnapshot document : documents) {
            players.add(document.getId());
        }
        // sort with comparator
        return players;
    }
}
