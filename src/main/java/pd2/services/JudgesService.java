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
import pd2.model.sodi.Sods;
import pd2.model.t.T;
import pd2.utils.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class JudgesService {

    @Autowired
    Firestore database;

    public void saveJudges(Spele spele) {
        List<T> judges = new ArrayList<>();
        int sodi = 0;
        for (Komanda komanda : spele.getKomanda()) {
            if (komanda.getSodi() != null) {
                for (Sods sods : komanda.getSodi().getSodi()) {
                    sodi += 1;
                }
            }
        }
        for (T t : spele.getT()) {
            t.setSodi(sodi);
            t.setVt("T");
            judges.add(t);
        }
        T vt = spele.getVt();
        vt.setSodi(sodi);
        vt.setVt("VT");
        judges.add(vt);

        for(T t : judges) {
            T firT = getJudge(t.getVt() + " " + t.getVards() + " " + t.getUzvards());
            if (firT != null) {
                firT.setSodi(t.getSodi());
                database.collection("Judges").document(firT.getVt() + " " + firT.getVards() + " " + firT.getUzvards()).set(firT);
            } else {
                database.collection("Judges").document(t.getVt() + " " + t.getVards() + " " + t.getUzvards()).set(t);
            }
            System.out.println(t.toString());
        }

    }

    public T getJudge(String ref) {
        DocumentReference docRef = database.collection("Judges").document(ref);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        T judge = null;
        if (document.exists()) {
            judge = document.toObject(T.class);
            System.out.println(judge);
        } else {
            System.out.println("Creation of new judge: " + ref);
        }
        return judge;
    }

    public List<T> getJudges() {
        //Utilities.date("00:53:00", "00:43:00");
        List<T> judges = new ArrayList<>();
        ApiFuture<QuerySnapshot> future =
                database.collection("Judges").get();
        List<DocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (DocumentSnapshot document : documents) {
            judges.add(document.toObject(T.class));
        }
        // sort with comparator
        Utilities.strictJudges(judges);
        return judges;
    }
}