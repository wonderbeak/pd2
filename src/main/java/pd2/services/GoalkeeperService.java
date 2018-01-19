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
import pd2.model.rest.Goalkeeper;
import pd2.model.sodi.Sods;
import pd2.model.speletaji.Speletajs;
import pd2.model.t.T;
import pd2.model.varti.VG;
import pd2.utils.Utilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class GoalkeeperService {

    @Autowired
    Firestore database;

    public void saveGoalkeeper(Speletajs gamer, Komanda win) {
        String ref = gamer.getKomanda() + " " + gamer.getNr();
        Goalkeeper goalkeeper = getGoalkeeper(ref, gamer);
        if (win.getVarti() != null && goalkeeper.getActive().equals("Y")) {
            int ok = 0;
            for (VG vg : win.getVarti().getVarti()) {
                ok += 1;
            }
            goalkeeper.setGoals(ok);
            goalkeeper.setGames(1);
            goalkeeper.setAverage(goalkeeper.getGoals()/goalkeeper.getGames());
        } else if (goalkeeper.getActive().equals("Y")) {
            goalkeeper.setGames(1);
            if (goalkeeper.getGoals() != 0)
                goalkeeper.setAverage(goalkeeper.getGoals()/goalkeeper.getGames());
            else
                goalkeeper.setAverage(goalkeeper.getGoals());
        }
        database.collection("Goalkeepers").document(ref).set(goalkeeper);
    }

    public Goalkeeper getGoalkeeper(String ref, Speletajs gamer) {
        DocumentReference docRef = database.collection("Goalkeepers").document(ref);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Goalkeeper goalkeeper = null;
        if (document.exists()) {
            goalkeeper = document.toObject(Goalkeeper.class);
        } else {
            goalkeeper = new Goalkeeper(gamer, 0);
            System.out.println("Creation of new goalkeeper: " + ref);
        }
        return goalkeeper;
    }

    public List<Goalkeeper> getGoalkeepers() {
        List<Goalkeeper> goalkeepers = new ArrayList<>();
        ApiFuture<QuerySnapshot> future =
                database.collection("Goalkeepers").get();
        List<DocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (DocumentSnapshot document : documents) {
            if (document.toObject(Goalkeeper.class).getGames() != 0) goalkeepers.add(document.toObject(Goalkeeper.class));
        }
        // sort with comparator
        Utilities.goalkeepers(goalkeepers);
        if (goalkeepers.size() < 5)
            return goalkeepers;
        else
            return goalkeepers.subList(0,4);
    }
}