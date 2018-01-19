package pd2.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pd2.model.Event;
import pd2.model.Spele;
import pd2.model.rest.Place;
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
    JudgesService judgesService;

    public boolean getEvent(String ref) {
        DocumentReference docRef = database.collection("Games").document(ref);
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
        if (getEvent(name)) {
            database.collection("Games").document(name).set(event.getSpele());
            savePlace(event.getSpele());
            tableService.saveTable(event.getSpele());
            playersService.saveSpeletajs(event.getSpele());
            judgesService.saveJudges(event.getSpele());

        } else {
           System.out.println("Event already saved!");
        }
    }

    public void savePlace(Spele spele) {
        Place place = getPlace(spele.getVieta());
        if (place == null) {
            place = new Place(spele.getVieta(), spele.getSkatitaji(), 1);
        } else {
            place.setCroud(spele.getSkatitaji());
            place.setGames(1);
        }
        database.collection("Places").document(place.getName()).set(place);
    }

    public Place getPlace(String ref) {
        DocumentReference docRef = database.collection("Places").document(ref);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Place place = null;
        if (document.exists()) {
            place = document.toObject(Place.class);
            System.out.println(place.toString());
        } else {
            System.out.println("Creation of new place: " + ref);
        }
        return place;
    }

    public List<Place> getPlaces() {
        List<Place> places = new ArrayList<>();
        ApiFuture<QuerySnapshot> future =
                database.collection("Places").get();
        List<DocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (DocumentSnapshot document : documents) {
            places.add(document.toObject(Place.class));
        }
        // sort with comparator
        return places;
    }
}
