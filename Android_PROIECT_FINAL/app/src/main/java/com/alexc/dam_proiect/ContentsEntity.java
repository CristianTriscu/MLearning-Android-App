package com.alexc.dam_proiect;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contents")
public class ContentsEntity {
    @PrimaryKey(autoGenerate = true)
    public int cid;

    @ColumnInfo(name = "stage_id")
    public int stageId;

    @ColumnInfo(name = "content_string")
    public String contentString;

    @ColumnInfo(name = "lesson_id")
    public int lid; // 1,2 > java 1,2 ||| 3,4 > android 1,2 ||| 5,6 > xml 1,2

    public ContentsEntity() {
        //
    }

    public ContentsEntity(int stageId, String contentString, int lessonId) {
        this.stageId = stageId;
        this.contentString = contentString;
        this.lid = lessonId;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public String getContentString() {
        return contentString;
    }

    public void setContentString(String contentString) {
        this.contentString = contentString;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    @Override
    public String toString() {
        return "Content{" +
                "cid='" + cid + '\'' +
                ", stage='" + stageId + '\'' +
                ", contentString='" + contentString + '\'' +
                ", lid='" + lid + '\'' +
                '}';
    }
}
