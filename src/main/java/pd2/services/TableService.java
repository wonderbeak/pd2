package pd2.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pd2.model.Event;
import pd2.model.Spele;
import pd2.model.komandas.Komanda;
import pd2.model.mainas.Maina;
import pd2.model.rest.Participant;
import pd2.model.sodi.Sods;
import pd2.model.speletaji.Speletajs;
import pd2.model.varti.VG;
import pd2.utils.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TableService {

    @Autowired
    Firestore database;

    public void saveTable(Spele spele) {
            List<Participant> participants = new ArrayList<>();
            for (Komanda komanda : spele.getKomanda()) {
                participants.add(getParticipant(komanda.getNosaukums()));
            }

            for (Participant participant : Utilities.calculate(spele, participants)) {
                database.collection("Table").document(participant.getName()).set(participant);
            }
        }


    public List<Participant> getTable() {
        //Utilities.date("00:53:00", "00:43:00");
        List<Participant> table = new ArrayList<>();
        ApiFuture<QuerySnapshot> future =
                database.collection("Table").get();
        List<DocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (DocumentSnapshot document : documents) {
            table.add(document.toObject(Participant.class));
        }
        // sort with comparator
        Utilities.topTable(table);
        return table;
    }


    public Participant getParticipant(String ref) {
        DocumentReference docRef = database.collection("Table").document(ref);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (document.exists()) {
            Participant participant = document.toObject(Participant.class);
            return participant;
        } else {
            Participant participant = new Participant();
            participant.setName(ref);
            return participant;
        }
    }
}
