package pd2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import pd2.model.Event;
import pd2.model.rest.Goalkeeper;
import pd2.model.rest.Participant;
import pd2.model.speletaji.Speletajs;
import pd2.model.t.T;
import pd2.services.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class Controller {
    static Logger log = Logger.getLogger(Controller.class.getName());

    @Autowired
    EventService eventService;
    @Autowired
    JudgesService judgesService;
    @Autowired
    PlayersService playersService;
    @Autowired
    TableService tableService;
    @Autowired
    GoalkeeperService goalkeeperService;

    @RequestMapping(value="/futbols", method = RequestMethod.POST, consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public HttpEntity<Event> input(@RequestBody Event event) {
        log.info(event.toString());
        eventService.saveEvent(event);
        return new HttpEntity<Event>(event);
    }
    @RequestMapping(value="/top", method = RequestMethod.GET, headers = "Accept=application/json")
    public HttpEntity<List<Speletajs>> top() {
        return new HttpEntity<List<Speletajs>>(playersService.getSpeletaji());
    }

    @RequestMapping(value="/players", method = RequestMethod.GET, headers = "Accept=application/json")
    public HttpEntity<List<String>> players() {
        return new HttpEntity<List<String>>(playersService.getPlayers());
    }

    @RequestMapping(value="/goalkeepers", method = RequestMethod.GET, headers = "Accept=application/json")
    public HttpEntity<List<Goalkeeper>> goalkeepers() {
        return new HttpEntity<List<Goalkeeper>>(goalkeeperService.getGoalkeepers());
    }

    @RequestMapping(value="/judges", method = RequestMethod.GET, headers = "Accept=application/json")
    public HttpEntity<List<T>> judges() {
        return new HttpEntity<List<T>>(judgesService.getJudges());
    }

    @RequestMapping(value="/table", method = RequestMethod.GET, headers = "Accept=application/json")
    public HttpEntity<List<Participant>> table() {
        return new HttpEntity<List<Participant>>(tableService.getTable());
    }

    @RequestMapping(value="/player/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public HttpEntity<Speletajs> player(@PathVariable("id") String id) {
        return new HttpEntity<Speletajs>(playersService.getSpeletajs(id));
    }
}
