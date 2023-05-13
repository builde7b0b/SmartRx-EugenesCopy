package com.sei.smartrx.controller;

import com.sei.smartrx.models.Medication;
import com.sei.smartrx.models.Prescription;
import com.sei.smartrx.models.User;
import com.sei.smartrx.models.request.LoginRequest;
import com.sei.smartrx.repository.PrescriptionRepository;
import com.sei.smartrx.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class PrescriptionController {

    private PrescriptionService prescriptionService;

    @Autowired
    public void setPrescriptionService(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    /**
     * GET: endpoint http://localhost:8080/api/prescriptions
     * @return List of Prescriptions for a specific user
     */
    @GetMapping(path = "/prescriptions")
    public List<Prescription> getAllPrescriptionsForUser()  {
        return prescriptionService.getAllPrescriptionsForUser();
    }

    /**
     * GET: endpoint http://localhost:8080/api/newRequest/prescriptions/1
     * @param Long medicationID
     * @return Prescription the user has requested to be refilled with updates refill #
     */
    @GetMapping(path="/prescriptions/newRequest/{prescriptionId}")
    public Prescription requestPrescriptionRefill(@PathVariable Long prescriptionId){
        return prescriptionService.requestPrescriptionRefill(prescriptionId);
    }

    /**
     * GET: endpoint http://localhost:8080/api/prescriptions/medications/1
     * GET ONE MEDICATION, gets a medication by its ID.
     * @param medicationId medicationID
     * @return a Medication object
     */
    @GetMapping(path="/prescriptions/medications/{medicationId}")
    public Medication getAMedication(@PathVariable Long medicationId){
        return prescriptionService.getAMedication(medicationId);
    }

    /**
     * GET: endpoint http://localhost:8080/api/pharmacist/prescriptions
     * GET ALL PRESCRIPTIONS, must have pharmacist userprofile role to access this through user servide.
     * @return a list of all prescriptions
     */
    @GetMapping (path="/pharmacist/prescriptions")
    public List<Prescription> allPrescriptions(){
        return prescriptionService.getAllPrescriptions();
    }

    /**
     *GET A SPECIFIC PRESCRIPTION BY ID. Must be a pharmacist to see any prescription, verifies in prescription service.
     * @param Long prescriptionId
     * @return Prescription
     */

    @PutMapping(path="/pharmacist/prescription/{prescriptionId}")
    public Prescription updatePrescription(@RequestBody Prescription prescriptionObject,
                                           @PathVariable Long prescriptionId) {
        return prescriptionService.updatePrescription(prescriptionObject, prescriptionId);
    }


    @GetMapping (path="/pharmacist/prescriptions/{prescriptionId}")
    public Prescription getAPrescriptionId(@PathVariable Long prescriptionId){
        return prescriptionService.getAPrescriptionsById(prescriptionId);
    }


    @PostMapping(path = "/pharmacist/prescriptions/{userId}/{medicationId}")
    public Prescription createPrescriptionForUser(@PathVariable Long userId, @PathVariable Long medicationId, @RequestBody Prescription prescriptionObject) {
        return prescriptionService.createPrescriptionForUser(userId, medicationId, prescriptionObject);
    }

    @DeleteMapping(path = "/pharmacist/prescriptions/{prescriptionId}")
    public Prescription deletePrescription(@PathVariable Long prescriptionId ) {
        return prescriptionService.deletePrescription(prescriptionId);
    }
}
