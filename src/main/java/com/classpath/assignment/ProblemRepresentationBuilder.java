package com.classpath.assignment;

import java.util.*;
import java.util.stream.Collectors;

import com.classpath.assignment.constraints.ConstraintIF;
import com.classpath.assignment.ga.EvaluationFunction;
import com.classpath.assignment.constraints.SetDom;
import com.classpath.assignment.constraints.Variable;
import com.classpath.assignment.constraints.generator.FollowsConGenerator;
import com.classpath.assignment.constraints.generator.OnePerSlotConGenerator;
import com.classpath.assignment.constraints.generator.PerShiftConGenerator;
import com.classpath.assignment.constraints.generator.SlotsDiffConGenerator;
import com.classpath.assignment.model.ErgonomicRanking;
import com.classpath.assignment.model.TimeSlot;
import com.classpath.assignment.model.SkillLevel;
import com.classpath.assignment.model.Worker;
import com.classpath.assignment.model.WorkerRole;
import com.classpath.assignment.model.WorkerWorkstationLevel;
import com.classpath.assignment.model.Workstation;
import com.classpath.assignment.model.WorkstationTimeSlots;

public class ProblemRepresentationBuilder {

	private WorkstationTimeSlots workstationTimeSlots;
	private List<Worker> workers ;
	private List<WorkerWorkstationLevel> workerWorkstationLevel ;
	private List<Variable<String>> variables ;
	private EvaluationFunction eval ;
	
	private Workstation createWorkstation(String details) {
		String[] wDetails = details.split(",") ;
		String id = wDetails[0] ;
		String name = wDetails[1] ;
		ErgonomicRanking ranking = ErgonomicRanking.valueOf(wDetails[2]) ;
		return new Workstation(id, name, ranking) ; 
	}
	
	public ProblemRepresentationBuilder addWorkstations(List<String> lines) {
		List<Workstation> wkstns = createWkstns(lines) ;
		wkstns.sort(Comparator.comparing(Workstation::getId));
		this.workstationTimeSlots = new WorkstationTimeSlots(createWkstns(lines)) ;
		return this ;
	}
	
	public List<Workstation> createWkstns(List<String> lines) {
		List<Workstation> result = new ArrayList<>() ;
		for (String line : lines) {
			result.add(createWorkstation(line)) ;
		}
		return result ;
	}
		
	public ProblemRepresentationBuilder addWorkers(List<String> lines) {
		this.workers = createWorkers(lines) ;
		return this ;
	}
	
	private Worker createWorker(String details) {
		String[] wDetails = details.split(",") ;
		String id = wDetails[0] ;
		WorkerRole role = WorkerRole.valueOf(wDetails[1]) ;
		String fullName = wDetails[2] ;
		return new Worker(role, id, fullName) ;
	}

	public List<Worker> createWorkers(List<String> lines) {
		List<Worker> result = new ArrayList<>() ;
		for (String line : lines) {
			result.add(createWorker(line)) ;
		}
		return result ;
	}
	
	public ProblemRepresentationBuilder addOperatorSkillLevels(List<String> lines) {
		List<WorkerWorkstationLevel> result = new ArrayList<>() ;
		for (String line : lines) {
			String[] wDetails = line.split(",") ;			
			String operName = wDetails[0] ;
			String wkstationName = wDetails[1] ;
			String skill = wDetails[2] ;
			Optional<TimeSlot> wks = this.workstationTimeSlots.getTimeSlots().stream().filter(w -> w.getWorkstation().getName().equals(wkstationName)).findFirst() ;
			if (!wks.isPresent()) {
				throw new RuntimeException("No workstation named " + wkstationName) ;
			}
			Optional<Worker> wkr = this.workers.stream().filter(w -> w.getFullName().equals(operName)).findFirst() ;
			if (!wkr.isPresent()) {
				throw new RuntimeException("No operator named " + operName) ;
			}
			SkillLevel level = SkillLevel.valueOf(skill) ;
			result.add(new WorkerWorkstationLevel(wkr.get(), wks.get().getWorkstation(), level)) ;
		}
		this.workerWorkstationLevel = result ;
		return this ;
	}
	
	private Map<String, Variable<String>> createVariableDomains() {
		Map<String, Variable<String>> workstationVariableMap = new HashMap<>();
		for (WorkerWorkstationLevel wwl : this.workerWorkstationLevel) {
			if (wwl.isCanOperate()) {
				String workstationId = wwl.getWorkstation().getId() ;
				Variable<String> workstationVar = null ;
				Worker worker = wwl.getWorker() ;
				if (workstationVariableMap.containsKey(workstationId)) {
					workstationVar = workstationVariableMap.get(workstationId) ;
				} else {
					workstationVar = new Variable<>(new SetDom<>()) ;
				}
				workstationVar.getDomain().addDomVal(worker.getId());
				workstationVariableMap.put(workstationId, workstationVar) ;
			}
		}
		return workstationVariableMap ;
	}
	
	public ProblemRepresentationBuilder createVariables() {
		this.variables = createUnassignedVariables() ;
		return this ;
	}
	
	private List<Variable<String>> createUnassignedVariables() {
		List<Variable<String>> variables = new ArrayList<>() ;
		Map<String, Variable<String>> variableDomainMap = createVariableDomains() ;
		for (TimeSlot workstation : workstationTimeSlots.getTimeSlots()) {
			// each workstation has 3 sessions
			Variable<String> var = variableDomainMap.get(workstation.getWorkstation().getId()) ;
			for (int i = 0; i< WorkstationTimeSlots.NO_OF_TIMESLOTS; i++) {
				variables.add(new Variable<String>(var)) ;
			}
		}
		return variables ;
	}
	
	public List<Variable<String>> getVariables() {
		return variables;
	}


	private Set<String> getWorkerIdsOnWorkstations(List<TimeSlot> workstations,
													List<WorkerWorkstationLevel> workerWorkstationLevel) {
		Set<String> workstationIds = workstations.stream().map(w -> w.getWorkstation().getId()).collect(Collectors.toSet()) ;
		return workerWorkstationLevel.stream().filter(wwl -> workstationIds.contains(wwl.getWorkstation().getId()))
										.map(wwl -> wwl.getWorker().getId()).collect(Collectors.toSet()) ;
	}

	public EvaluationFunction getEvalFunction() {
		if (this.eval == null) {
			this.eval = new EvaluationFunction(generateHardConstraints(), generateSoftConstraints()) ;
		}
		return this.eval ;
	}

	private List<ConstraintIF> generateSoftConstraints() {
		return new SlotsDiffConGenerator(workstationTimeSlots.getAll()).getConstraints();
	}

	private List<ConstraintIF> generateHardConstraints() {
		List<ConstraintIF> cons = new ArrayList<>() ;
		List<TimeSlot> allWorkstations = workstationTimeSlots.getAll() ;
		List<TimeSlot> aWorkstations = workstationTimeSlots.getAllWithRanking(ErgonomicRanking.A) ;
		List<TimeSlot> bWorkstations = workstationTimeSlots.getAllWithRanking(ErgonomicRanking.B) ;
		Set<String> aWorkstationUserIds = getWorkerIdsOnWorkstations(aWorkstations, workerWorkstationLevel) ;
		Set<String> bWorkstationUserIds = getWorkerIdsOnWorkstations(bWorkstations, workerWorkstationLevel) ;
		cons.addAll(new OnePerSlotConGenerator(allWorkstations).getConstraints()) ;
		cons.addAll(new FollowsConGenerator(aWorkstations, bWorkstations).getConstraints()) ;
		cons.addAll(new PerShiftConGenerator(aWorkstations,aWorkstationUserIds, 1).getConstraints()) ;
		cons.addAll(new PerShiftConGenerator(bWorkstations,bWorkstationUserIds, 2).getConstraints()) ;
		return cons;
	}

}
