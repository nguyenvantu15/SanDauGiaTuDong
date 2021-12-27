package com.ute.sandaugiatudong.beans;

public class ProductBidderAuction {
    private int idPro;
    private int idBidder;

    public int getIdPro() {
        return idPro;
    }

    public int getIdBidder() {
        return idBidder;
    }

    public ProductBidderAuction(int idPro, int idBidder) {
        this.idPro = idPro;
        this.idBidder = idBidder;
    }

    public ProductBidderAuction() {
    }
}
