package controller;

import model.*;

import java.util.List;

public interface Storage {

    //---------------------------------------------------------------------
    List<Destillat> getDestillater();
    void storeDestillat(Destillat d);

    //---------------------------------------------------------------------
    List<Fad> getFade();
    void storeFad(Fad f);

    //---------------------------------------------------------------------
    List<Lager> getLagere();
    void storeLager(Lager l);

    //---------------------------------------------------------------------
    List<Whisky> getWhiskyer();
    void storeWhisky(Whisky w);

    //---------------------------------------------------------------------
}
