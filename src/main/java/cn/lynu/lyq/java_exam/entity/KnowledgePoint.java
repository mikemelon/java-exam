package cn.lynu.lyq.java_exam.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class KnowledgePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Override
    public String toString(){
        return this.getName();
    }

    public KnowledgePoint() {

    }

    public KnowledgePoint(String name){
        setName(name);
    }
}
