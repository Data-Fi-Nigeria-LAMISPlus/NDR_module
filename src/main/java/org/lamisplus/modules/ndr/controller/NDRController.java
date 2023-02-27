package org.lamisplus.modules.ndr.controller;


import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.ndr.domain.dto.NDREligibleClient;
import org.lamisplus.modules.ndr.domain.dto.NdrXmlStatusDto;
import org.lamisplus.modules.ndr.service.NDRService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/ndr")
@RequiredArgsConstructor
@Slf4j
public class NDRController {
    private final NDRService ndrService;

    private final SimpMessageSendingOperations messagingTemplate;

    //    @GetMapping("/message-header/{personId}")
//    public void generateMessageHeaderType(@PathVariable("personId") Long personId){
//        xmlTestService.shouldPrintMessageHeaderTypeXml (personId);
//    }
//
//    @GetMapping("/patient-demographic/{personId}")
//    public void generatePrintPatientDemographicsTypeXml(@PathVariable("personId") Long personId) {
//        xmlTestService.shouldPrintPatientDemographicsTypeXml (personId);
//    }
//
//    @GetMapping("/patient-address/{personId}")
//    public void generatePatientAddressTypeXml(@PathVariable("personId") Long personId){
//        xmlTestService.shouldPrintPatientAddressTypeXml (personId);
//    }
//
//    @GetMapping("/patient-common-question/{personId}")
//    public void generatePatientCommonQuestionXml(@PathVariable("personId") Long personId){
//        xmlTestService.shouldPrintPatientCommonQuestionsTypeXml (personId);
//    }
//@GetMapping("/patient-specific-question/{personId}")
//    public void generatePatientSpecificQuestionXml(@PathVariable("personId") Long personId){
//        xmlTestService.shouldPrintPatientConditionSpecificQuestionsTypeXml (personId);
//    }
//    @GetMapping("/patient-clinical_encouters/{personId}")
//    public void generatePatientClinicalEncounterXml(@PathVariable("personId") Long personId){
//        xmlTestService.shouldPrintPatientConditionEncountersTypeXml (personId);
//    }
    @GetMapping("/generate/{personId}")
    public void generatePatientXml(@PathVariable("personId") String personId, @RequestParam("facility") Long facility) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String pushIdentifier = UUID.randomUUID().toString();
        ndrService.shouldPrintPatientContainerXml (personId, facility, true, pushIdentifier);
        log.info("Total time taken to generate a file: {}", stopwatch.elapsed().toMillis());
    }

    @GetMapping("/generate")
    public void generateFacilityPatientXmls(
            @RequestParam List<Long> facilityIds,
             @RequestParam boolean isInitial
    ){
        messagingTemplate.convertAndSend("/topic/ndr-status", "start");
        Stopwatch stopwatch = Stopwatch.createStarted();
        facilityIds.forEach (facilityId -> ndrService.generateNDRXMLByFacility(facilityId,isInitial));
        messagingTemplate.convertAndSend("/topic/ndr-status", "end");
        log.info("Total time taken to generate a file: {}", stopwatch.elapsed().toMillis());
    }
    
    @GetMapping("/generate/patients")
    public void generateFacilitySelectedPatientXmls(
            @RequestParam List<Long> facilityIds,
            @RequestParam boolean initial,
            @RequestParam List<String> patientIds
    ){
        messagingTemplate.convertAndSend("/topic/ndr-status", "start");
        Stopwatch stopwatch = Stopwatch.createStarted();
        facilityIds.forEach (facilityId -> ndrService.generateNDRXMLByFacilityAndListOfPatient(facilityId,initial,patientIds));
        messagingTemplate.convertAndSend("/topic/ndr-status", "end");
        log.info("Total time taken to generate a file: {}", stopwatch.elapsed().toMillis());
    }
    
    @GetMapping("/patients")
    public ResponseEntity<List<NDREligibleClient>> getNDREligibleClients(@RequestParam Long facilityId, String search){
        return  ResponseEntity.ok(ndrService.getNDRClientList(facilityId, search));
    }
    
    


    @GetMapping("/download/{file}")
    public void downloadFile(@PathVariable String file, HttpServletResponse response) throws IOException {
        ByteArrayOutputStream baos = ndrService.downloadFile (file);
        response.setHeader ("Content-Type", "application/octet-stream");
        response.setHeader ("Content-Disposition", "attachment;filename=" + file + ".zip");
        response.setHeader ("Content-Length", Integer.toString (baos.size ()));
        OutputStream outputStream = response.getOutputStream ();
        outputStream.write (baos.toByteArray ());
        outputStream.close ();
        response.flushBuffer ();
    }

    @GetMapping("/files")
    public Collection<NdrXmlStatusDto> listFiles() {
        return ndrService.getNdrStatus ();
    }

}
