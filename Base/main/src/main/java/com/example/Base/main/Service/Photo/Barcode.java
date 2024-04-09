package com.example.Base.main.Service.Photo;

import com.google.gson.annotations.SerializedName;

public class Barcode {

    @SerializedName("data")
    private DataDTO data;
    public static class DataDTO {
        @SerializedName("drug")
        private DrugDTO drug;
        @SerializedName("barcode")
        private BarcodeDTO barcode;

        public DrugDTO getDrug() {
            return drug;
        }

        public void setDrug(DrugDTO drug) {
            this.drug = drug;
        }

        public BarcodeDTO getBarcode() {
            return barcode;
        }

        public void setBarcode(BarcodeDTO barcode) {
            this.barcode = barcode;
        }

        public static class DrugDTO {
            @SerializedName("drug_approval_number_id")
            private String drugApprovalNumberId;
            @SerializedName("drug_generic_id")
            private String drugGenericId;
            @SerializedName("approval_number")
            private String approvalNumber;
            @SerializedName("drug_package_id")
            private String drugPackageId;
            @SerializedName("package")
            private String packageX;

            public String getDrugApprovalNumberId() {
                return drugApprovalNumberId;
            }

            public void setDrugApprovalNumberId(String drugApprovalNumberId) {
                this.drugApprovalNumberId = drugApprovalNumberId;
            }

            public String getDrugGenericId() {
                return drugGenericId;
            }

            public void setDrugGenericId(String drugGenericId) {
                this.drugGenericId = drugGenericId;
            }

            public String getApprovalNumber() {
                return approvalNumber;
            }

            public void setApprovalNumber(String approvalNumber) {
                this.approvalNumber = approvalNumber;
            }

            public String getDrugPackageId() {
                return drugPackageId;
            }

            public void setDrugPackageId(String drugPackageId) {
                this.drugPackageId = drugPackageId;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }
        }
        public static class BarcodeDTO {
            @SerializedName("barcode")
            private String barcode;
            @SerializedName("name")
            private String name;
            @SerializedName("description")
            private Object description;
            @SerializedName("package")
            private String packageX;
            @SerializedName("company_name")
            private String companyName;

            public String getBarcode() {
                return barcode;
            }

            public void setBarcode(String barcode) {
                this.barcode = barcode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }
        }
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}
