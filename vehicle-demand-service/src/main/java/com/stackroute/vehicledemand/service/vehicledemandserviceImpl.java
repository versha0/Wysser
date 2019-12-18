package com.stackroute.vehicledemand.service;

import com.google.gson.Gson;
import com.stackroute.vehicledemand.domain.acceptedRetailerRequest;
import com.stackroute.vehicledemand.domain.newRetailerDemand;
import com.stackroute.vehicledemand.domain.rejectedRetailerRequest;
import com.stackroute.vehicledemand.domain.retailerdemand;
import com.stackroute.vehicledemand.domain.DateDemand;
import com.stackroute.vehicledemand.domain.TimeSlot;
import com.stackroute.vehicledemand.repository.AcceptedRetailerDemandRepository;
import com.stackroute.vehicledemand.repository.newRetailerDemandRepository;
import com.stackroute.vehicledemand.repository.rejectedRetailerDemand;
import com.stackroute.vehicledemand.repository.vehicledemandrepository;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class vehicledemandserviceImpl implements vehicledemandservice {
    @Autowired
    vehicledemandrepository vehicledemandrepository;

    @Autowired
    newRetailerDemandRepository newRetailerDemandRepository;
    @Autowired
    AcceptedRetailerDemandRepository acceptedRetailerDemandRepository;
    @Autowired
    rejectedRetailerDemand rejectedRetailerDemand;
    

    @Override
    public retailerdemand savenewvehicledemand(retailerdemand retailerdemand) {
        vehicledemandrepository.save(retailerdemand);
        return retailerdemand;
    }

    @Override
    public List<newRetailerDemand> getvehicledemand() {
        return newRetailerDemandRepository.findAll();
    }

    @Override
    public newRetailerDemand savenewvehicledemandbyretailer(newRetailerDemand newRetailerDemand) {
        newRetailerDemandRepository.save(newRetailerDemand);

        return newRetailerDemand;
    }

    @Override
    public List<newRetailerDemand> getallvehicledemanded(String companyName) {
        // return newRetailerDemandRepository.findAll();
        System.out.println(companyName);
        List<newRetailerDemand> test= newRetailerDemandRepository.findByCompanyName(companyName);
        System.out.println(test.toString());
        return test;
    }

    @Override
    public boolean deletebyId(BigInteger Id) {
        newRetailerDemandRepository.deleteById(Id);

        return true;
    }

    @Override
    public acceptedRetailerRequest savenewaccepetedVehicleDemand(acceptedRetailerRequest aacceptedRetailerRequest) {
        acceptedRetailerDemandRepository.save(aacceptedRetailerRequest);
        return aacceptedRetailerRequest;
    }

    @Override
    public rejectedRetailerRequest savenewrejectedVehicleDemand(rejectedRetailerRequest rejectedRetailerRequest) {
        rejectedRetailerDemand.save(rejectedRetailerRequest);
        return rejectedRetailerRequest;
    }

    @Override
    public List<newRetailerDemand> searchbyretailerId(int id) {
        return this.newRetailerDemandRepository.findByRetailerId(id);
    }

    @Override
    public List<rejectedRetailerRequest> findByRetailerIdinrejectedlist(int retailerId) {
        return this.rejectedRetailerDemand.findByRetailerId(retailerId);
    }

    @Override
    public List<acceptedRetailerRequest> findByRetailerIdinacceptedlist(int retailerId) {
        return this.acceptedRetailerDemandRepository.findByRetailerId(retailerId);
    }

    @Override

    public List<acceptedRetailerRequest> updateremainingvolumeinvehicle(int retailerId, String slot,int volumebooked) {
         System.out.println(retailerId+slot);
         List<acceptedRetailerRequest> bookedVehicles= this.acceptedRetailerDemandRepository.findByRetailerIdAndSlot(retailerId, slot);
         System.out.println(bookedVehicles.toString());
         List<acceptedRetailerRequest> sortedtemp = new ArrayList<acceptedRetailerRequest>();
         List<acceptedRetailerRequest> bookedvehiclesarraylist= new ArrayList<acceptedRetailerRequest>();
         acceptedRetailerRequest[] bookedvehiclesarray= bookedVehicles.toArray(new acceptedRetailerRequest[bookedVehicles.size()]);
        acceptedRetailerRequest tempobj = new acceptedRetailerRequest();
         for(int i=0;i<bookedvehiclesarray.length;i++){
//             int temp=bookedvehiclesarray[i].getCapacity();
             for(int j=i+1;j<bookedvehiclesarray.length;j++){
               if(bookedvehiclesarray[i].getCapacity() > bookedvehiclesarray[j].getCapacity()){
//                   temp=bookedvehiclesarray[j].getCapacity();
                   tempobj=bookedvehiclesarray[i];
                   bookedvehiclesarray[i]=bookedvehiclesarray[j];
                   bookedvehiclesarray[j]=tempobj;
               }
             }
//             sortedtemp.add(bookedvehiclesarray[i]);
         }
        //  System.out.println(bookedvehiclesarray[0].toString());
//        sortedtemp= Arrays.asList(bookedvehiclesarray);
         for(int i=0;i<bookedvehiclesarray.length;i++){
             if(bookedvehiclesarray[i].getRemainingCapacity()>= volumebooked){
                 int temp=bookedvehiclesarray[i].getRemainingCapacity()-volumebooked;
                 bookedvehiclesarray[i].setRemainingCapacity(temp);
                // acceptedRetailerRequest particularvehicle= acceptedRetailerDemandRepository.findByRetailerIdAndVehicleNumber(bookedvehiclesarray[i].getRetailerId(),bookedvehiclesarray[i].getVehicleNumber());
                acceptedRetailerDemandRepository.save(bookedvehiclesarray[i]);
                break;

             }
         }
         return Arrays.asList(bookedvehiclesarray);
    }

    public List<acceptedRetailerRequest> findByRetailerIdAndSlot(int retailerId, String slot) {
        List<acceptedRetailerRequest> list= this.acceptedRetailerDemandRepository.findByRetailerIdAndSlot(retailerId, slot);
        return list;
    }
//    @Override
//    public List<acceptedRetailerRequest> addordertoSlotvehicle(int retailerId, String slot,int volumebooked) {
//         List<acceptedRetailerRequest> bookedVehicles= this.acceptedRetailerDemandRepository.findByRetailerIdAndSlot(retailerId,slot);
//         List<acceptedRetailerRequest> sortedtemp;
//         List<acceptedRetailerRequest> bookedvehiclesarraylist= new ArrayList<acceptedRetailerRequest>();
//         Object[] bookedvehiclesarray= bookedvehiclesarraylist.toArray();
//        ListIterator<acceptedRetailerRequest> iterator = bookedVehicles.listIterator();


//        while (iterator1.hasNext()) {

//        }
//    }
    public DateDemand[] findByRetailerIdForOrder(int retailerId){
        List<acceptedRetailerRequest> vehicles = this.acceptedRetailerDemandRepository.findByRetailerId(retailerId);
        int slot1Volume = 0, slot2Volume = 0, slot3Volume = 0;
        for(acceptedRetailerRequest vehicle : vehicles){
            if(vehicle.getSlot().equals("slot1")){
                slot1Volume += vehicle.getRemainingCapacity();
            }else if(vehicle.getSlot().equals("slot2")){
                slot2Volume += vehicle.getRemainingCapacity();
            }else{
                slot3Volume += vehicle.getRemainingCapacity();
            }
        }
        DateDemand[] dateDemand = {new DateDemand(
            "today", new TimeSlot("11:00-13:00", slot1Volume), new TimeSlot("14:00-16:00", slot2Volume), new TimeSlot("17:00-19:00", slot3Volume)
        )};
        return dateDemand;

    }

}
