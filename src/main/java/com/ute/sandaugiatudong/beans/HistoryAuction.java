package com.ute.sandaugiatudong.beans;

import java.time.LocalDateTime;

public class HistoryAuction {
    int idPro;
    int idBidder;
    int priceMaxBidder;
    int priceIn;
    int bidderCur;
    LocalDateTime time;

    public int getIdPro() {
        return idPro;
    }

    public int getIdBidder() {
        return idBidder;
    }

    public int getPriceMaxBidder() {
        return priceMaxBidder;
    }

    public int getPriceIn() {
        return priceIn;
    }

    public int getBidderCur() {
        return bidderCur;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public HistoryAuction(int idPro, int idBidder, int priceMaxBidder, int priceIn, int bidderCur, LocalDateTime time) {
        this.idPro = idPro;
        this.idBidder = idBidder;
        this.priceMaxBidder = priceMaxBidder;
        this.priceIn = priceIn;
        this.bidderCur = bidderCur;
        this.time = time;
    }

    public HistoryAuction() {
    }
}
