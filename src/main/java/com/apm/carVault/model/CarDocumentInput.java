package com.apm.carVault.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

    @Entity
    public class CarDocumentInput {

        @Id
        @Column(name="car_document_id", nullable = false)
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Long id;

        @Lob
        @Column(name="document_content", nullable = true , columnDefinition = "LONGTEXT")
        private String content;

        @Column(name="document_type", nullable = true )
        private String documentType;

        @Column(name="document_name", nullable = true )
        private String documentName;

        @Column(name="document_date", nullable = true )
        private Date documentDate;

        @ManyToOne
        @JoinColumn(name = "car_id",
                nullable = false, updatable = true)
        private Car car;

        public CarDocumentInput() {
        }

        public CarDocumentInput(String content,String documentType, String documentName, Car car, Date date) {
            this.content = content;
            this.car = car;
            this.documentType=documentType;
            this.documentName=documentName;
            this.documentDate=date;
        }

        public Date getDocumentDate() {
            return documentDate;
        }

        public void setDocumentDate(Date documentDate) {
            this.documentDate = documentDate;
        }

        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
        }

        public String getDocumentName() {
            return documentName;
        }

        public void setDocumentName(String documentName) {
            this.documentName = documentName;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "CarDocument{" +
                    "content='" + content + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            com.apm.carVault.model.CarDocumentInput that = (com.apm.carVault.model.CarDocumentInput) o;
            return Objects.equals(content, that.content);
        }

        @Override
        public int hashCode() {
            return Objects.hash(content);
        }
    }
