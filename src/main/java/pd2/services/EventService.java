package pd2.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pd2.model.Event;
import pd2.model.rest.Participant;
import pd2.model.Spele;
import pd2.model.komandas.Komanda;
import pd2.model.mainas.Maina;
import pd2.model.sodi.Sods;
import pd2.model.speletaji.Speletajs;
import pd2.model.varti.VG;
import pd2.utils.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class EventService {

    @Autowired
    Firestore database;
    @Autowired
    PlayersService playersService;
    @Autowired
    TableService tableService;
    @Autowired
    JudgesService judgeService;

    public boolean getEvent(String ref) {
        DocumentReference docRef = database.collection("Speles").document(ref);
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
            return false;
        } else {
            return true;
        }
    }

    public void saveEvent(Event event) {
        String name = Utilities.buildId(event.getSpele());
        //if (getEvent(name)) {
            database.collection("Games").document(name).set(event.getSpele());
            //tableService.saveTable(event.getSpele());
            playersService.saveSpeletajs(event.getSpele());
            //judgeService.saveJudges(event.getSpele());

        //} else {
        //    System.out.println("Event already saved!");
        //}

    }

}
