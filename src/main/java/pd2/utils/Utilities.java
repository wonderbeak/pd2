package pd2.utils;

import pd2.model.Result;
import pd2.model.rest.Goalkeeper;
import pd2.model.rest.Participant;
import pd2.model.Spele;
import pd2.model.komandas.Komanda;
import pd2.model.speletaji.Speletajs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import pd2.model.t.T;
import pd2.model.varti.VG;


public class Utilities {

    public static String buildId(Spele spele) {
        String id = "(" + spele.getLaiks().replace("/","-") + ") ";
        for (Komanda komanda : spele.getKomanda()) {
            id += komanda.getNosaukums() + " ";
        }
        return id.trim();
    }

    public static List<Speletajs> top(List<Speletajs> list) {
        list.sort(Speletajs.SpeletajsComparator);
        return list;
    }

    public static List<Participant> topTable(List<Participant> list) {
        list.sort(Participant.TableComparator);
        return list;
    }

    public static List<T> strictJudges(List<T> list) {
        list.sort(T.JudgeComparator);
        return list;
    }

    public static List<Goalkeeper> goalkeepers(List<Goalkeeper> list) {
        list.sort(Goalkeeper.GoalkeeperComparator);
        return list;
    }

    public static List<Speletajs> evil(List<Speletajs> list) {
        list.sort(Speletajs.EvilComparator);
        return list;
    }

    private static boolean diff(String toDate){
        return fullTime("00:"+toDate).after(fullTime("00:60:00"));
    }

    public static Date fullTime(String time) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date fullTime = null;
        try {
            fullTime = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fullTime;
    }

    public static long sum(String one, String two) {
        long tm = 0;

        String[] arr = one.split(":");
        tm += Integer.parseInt(arr[2]);
        tm += 60 * Integer.parseInt(arr[1]);
        tm += 3600 * Integer.parseInt(arr[0]);


        String[] arr2 = two.split(":");
        tm += Integer.parseInt(arr2[2]);
        tm += 60 * Integer.parseInt(arr2[1]);
        tm += 3600 * Integer.parseInt(arr2[0]);

        return tm;

/*        long hh = tm / 3600;
        tm %= 3600;
        long mm = tm / 60;
        tm %= 60;
        long ss = tm;*/
    }

    public static long sum(String one) {
        long tm = 0;

        String[] arr = one.split(":");
        tm += Integer.parseInt(arr[2]);
        tm += 60 * Integer.parseInt(arr[1]);
        tm += 3600 * Integer.parseInt(arr[0]);

        return tm;
    }

    public static String total(long tm) {
        long hh = tm / 3600;
        tm %= 3600;
        long mm = tm / 60;
        tm %= 60;
        long ss = tm;
        return format(hh) + ":" + format(mm) + ":" + format(ss);
    }

    private static String format(long s){
        if (s < 10) return "0" + s;
        else return "" + s;
    }

    public static List<Participant> calculate(Spele spele, List<Participant> participants) {
        Komanda one = spele.getKomanda().get(0);
        Participant oneP = participants.get(0);
        Komanda two = spele.getKomanda().get(1);
        Participant twoP = participants.get(1);
        if (one.getVarti() != null) {
            if (two.getVarti() != null) {
                if (one.getVarti().getVarti().size() > two.getVarti().getVarti().size()) {
                    if (overtime(one.getVarti().getVarti())) {
                        oneP.setPostWin(1);
                        oneP.setVg(one.getVarti().getVarti().size());
                        oneP.setPoints(3);
                        oneP.setLg(two.getVarti().getVarti().size());
                        twoP.setPostLose(1);
                        twoP.setVg(two.getVarti().getVarti().size());
                        twoP.setLg(one.getVarti().getVarti().size());
                        twoP.setPoints(2);

                    } else {
                        oneP.setWin(1);
                        oneP.setVg(one.getVarti().getVarti().size());
                        oneP.setPoints(5);
                        oneP.setLg(two.getVarti().getVarti().size());
                        twoP.setLose(1);
                        twoP.setVg(two.getVarti().getVarti().size());
                        twoP.setLg(one.getVarti().getVarti().size());
                        twoP.setPoints(1);
                    }
                } else {
                    if (overtime(two.getVarti().getVarti())) {
                        twoP.setPostWin(1);
                        twoP.setVg(two.getVarti().getVarti().size());
                        twoP.setPoints(3);
                        twoP.setLg(one.getVarti().getVarti().size());
                        oneP.setPostLose(1);
                        oneP.setVg(one.getVarti().getVarti().size());
                        oneP.setLg(two.getVarti().getVarti().size());
                        oneP.setPoints(2);

                    } else {
                        twoP.setWin(1);
                        twoP.setVg(two.getVarti().getVarti().size());
                        twoP.setPoints(5);
                        twoP.setLg(one.getVarti().getVarti().size());
                        oneP.setLose(1);
                        oneP.setVg(one.getVarti().getVarti().size());
                        oneP.setLg(two.getVarti().getVarti().size());
                        oneP.setPoints(1);
                    }
                }
            } else {
                if (overtime(one.getVarti().getVarti())) {
                    oneP.setPostWin(1);
                    oneP.setVg(one.getVarti().getVarti().size());
                    oneP.setPoints(3);
                    twoP.setPostLose(1);
                    twoP.setLg(one.getVarti().getVarti().size());
                    twoP.setPoints(2);

                } else {
                    oneP.setWin(1);
                    oneP.setVg(one.getVarti().getVarti().size());
                    oneP.setPoints(5);
                    twoP.setLose(1);
                    twoP.setLg(one.getVarti().getVarti().size());
                    twoP.setPoints(1);
                }
            }
        } else if (two.getVarti() != null) {
            if (overtime(two.getVarti().getVarti())) {
                twoP.setPostWin(1);
                twoP.setVg(two.getVarti().getVarti().size());
                twoP.setPoints(3);
                oneP.setPostLose(1);
                oneP.setLg(two.getVarti().getVarti().size());
                oneP.setPoints(2);

            } else {
                twoP.setWin(1);
                twoP.setVg(two.getVarti().getVarti().size());
                twoP.setPoints(5);
                oneP.setLose(1);
                oneP.setLg(two.getVarti().getVarti().size());
                oneP.setPoints(1);
            }
        }
        participants.set(0, oneP);
        participants.set(1, twoP);
        return participants;
    }


    private static boolean overtime(List<VG> vgs) {
        for (VG vg : vgs) {
            if (diff(vg.getLaiks())) {
                return true;
            }
        }
        return false;
    }
}
