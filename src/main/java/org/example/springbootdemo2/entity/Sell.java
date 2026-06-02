package org.example.springbootdemo2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sell")
public class Sell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "goods")
    private String goods;

    @Column(name = "price")
    private Float price;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "state1")
    private String state1;

    @Column(name = "state2")
    private String state2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    @Override
    public String toString() {
        return "Sell{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goods='" + goods + '\'' +
                ", price=" + price +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", state1='" + state1 + '\'' +
                ", state2='" + state2 + '\'' +
                '}';
    }
}
