package com.mis.smakash.utils;

public class SynchDataRow {
    public String DateOfInstallation;
    public String VisitDate;
    public String ServiceIncome;
    public String ServiceEndDate;
    public String ServerUpdateDateTime;
    public String TractorPurchaseDate;
    public String HoursProvided;
    public String ProductId_id;
    public String CategoryId_id;
    public String CustomerName;
    public String MobileLogCount;
    public String ServiceStartDate;
    public String MobileCreatedDT;
    public String MobileEditedDT;
    public String MobileId;
    public String ServerInsertDateTime;
    public String CallTypeId_id;
    public String ServiceDemandDate;
    public String Mobile;
    public String Chassis;
    public String rating;

    public SynchDataRow(String DateOfInstallation,
                        String VisitDate,
                        String ServiceIncome,
                        String ServiceEndDate,
                        String ServerUpdateDateTime,
                        String TractorPurchaseDate,
                        String HoursProvided,
                        String ProductId_id,
                        String CategoryId_id,
                        String CustomerName,
                        String MobileLogCount,
                        String ServiceStartDate,
                        String MobileCreatedDT,
                        String MobileEditedDT,
                        String MobileId,
                        String ServerInsertDateTime,
                        String CallTypeId_id,
                        String ServiceDemandDate,
                        String Mobile,
                        String Chassis){
                this.DateOfInstallation = DateOfInstallation;
                this.VisitDate = VisitDate;
                this.ServiceIncome = ServiceIncome;
                this.ServiceEndDate = ServiceEndDate;
                this.ServerUpdateDateTime = ServerUpdateDateTime;
                this.TractorPurchaseDate = TractorPurchaseDate;
                this.HoursProvided = HoursProvided;
                this.ProductId_id = ProductId_id;
                this.CategoryId_id = CategoryId_id;
                this.CustomerName = CustomerName;
                this.MobileLogCount = MobileLogCount;
                this.ServiceStartDate = ServiceStartDate;
                this.MobileCreatedDT = MobileCreatedDT;
                this.MobileEditedDT = MobileEditedDT;
                this.MobileId = MobileId;
                this.ServerInsertDateTime = ServerInsertDateTime;
                this.CallTypeId_id = CallTypeId_id;
                this.ServiceDemandDate = ServiceDemandDate;
                this.Mobile = Mobile;
                this.Chassis = Chassis;
    }
}
