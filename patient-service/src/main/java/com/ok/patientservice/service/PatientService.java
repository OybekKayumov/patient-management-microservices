package com.ok.patientservice.service;

import com.ok.patientservice.dto.PatientRequestDTO;
import com.ok.patientservice.dto.PatientResponseDTO;
import com.ok.patientservice.exception.EmailAlreadyExistsException;
import com.ok.patientservice.exception.PatientNotFoundException;
import com.ok.patientservice.grpc.BillingServiceGrpcClient;
import com.ok.patientservice.mapper.PatientMapper;
import com.ok.patientservice.model.Patient;
import com.ok.patientservice.repo.PatientRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

	//? dependency injection
	private final PatientRepo patientRepo;

	private final BillingServiceGrpcClient billingServiceGrpcClient;

	public PatientService(PatientRepo patientRepo,
	                      BillingServiceGrpcClient billingServiceGrpcClient) {
		this.patientRepo = patientRepo;
		this.billingServiceGrpcClient = billingServiceGrpcClient;

	}
	//* dependency injection end

	public List<PatientResponseDTO> getPatients() {
		List<Patient> patients = patientRepo.findAll();

		List<PatientResponseDTO> patientResponseDTOs = patients.stream()
						.map(patient -> PatientMapper.toDTO(patient)).toList();

		return patientResponseDTOs;
	}

	public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

		if(patientRepo.existsByEmail(patientRequestDTO.getEmail())) {
			throw new EmailAlreadyExistsException
							("Email already exists: " + patientRequestDTO.getEmail());
		}

		Patient newPatient = patientRepo.save(
						PatientMapper.toModel(patientRequestDTO));

		billingServiceGrpcClient.createBillingAccount(
						newPatient.getId().toString(),
						newPatient.getName(),
						newPatient.getEmail());

		return PatientMapper.toDTO(newPatient);
	}

	public PatientResponseDTO updatePatient(
					UUID id,
					PatientRequestDTO patientRequestDTO) {

		Patient patient = patientRepo.findById(id).orElseThrow(
						() -> new PatientNotFoundException("Patient not found with ID: " + id));

		if(patientRepo.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
			throw new EmailAlreadyExistsException
							("Email already exists: " + patientRequestDTO.getEmail());
		}

		patient.setName(patientRequestDTO.getName());
		patient.setAddress(patientRequestDTO.getAddress());
		patient.setEmail(patientRequestDTO.getEmail());
		patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

		Patient updatedPatient = patientRepo.save(patient);
		return PatientMapper.toDTO(updatedPatient);
	}

	public void deletePatient(UUID id) {
		patientRepo.deleteById(id);
	}

}
