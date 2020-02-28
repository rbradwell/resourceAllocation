package com.classpath.assignment.ga;

import com.classpath.assignment.ProblemRepresentationBuilder;
import com.classpath.assignment.constraints.ConstraintIF;
import com.classpath.assignment.constraints.generator.FollowsConGenerator;
import com.classpath.assignment.constraints.generator.OnePerSlotConGenerator;
import com.classpath.assignment.constraints.generator.PerShiftConGenerator;
import com.classpath.assignment.constraints.generator.SlotsDiffConGenerator;
import com.classpath.assignment.model.ErgonomicRanking;
import com.classpath.assignment.model.TimeSlot;
import com.classpath.assignment.model.WorkstationTimeSlots;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EvaluationFunctionBuilder {

    private static EvaluationFunction eval ;
    private ProblemRepresentationBuilder prb;
    private WorkstationTimeSlots timeSlots;

    public EvaluationFunctionBuilder(ProblemRepresentationBuilder prb) {
        this.prb = prb;
        this.timeSlots = prb.getWorkstationTimeSlots();
    }

    public EvaluationFunction getEvalFunction() {
        if (eval == null) {
            eval = new EvaluationFunction(generateHardConstraints(), generateSoftConstraints()) ;
        }
        return eval ;
    }

    private List<ConstraintIF> generateSoftConstraints() {
        return new SlotsDiffConGenerator(timeSlots.getAll()).getConstraints();
    }

    private List<ConstraintIF> generateHardConstraints() {
        List<ConstraintIF> cons = new ArrayList<>() ;
        cons.addAll(getOnePerSlot()) ;
        cons.addAll(getFollowsCons(ErgonomicRanking.A, ErgonomicRanking.B)) ;
        cons.addAll(getPerShiftCons(ErgonomicRanking.A, 1)) ;
        cons.addAll(getPerShiftCons(ErgonomicRanking.B, 2)) ;
        return cons;
    }

    private List<ConstraintIF> getOnePerSlot() {
        return new OnePerSlotConGenerator(timeSlots.getAll()).getConstraints();
    }

    private List<ConstraintIF> getFollowsCons(ErgonomicRanking a, ErgonomicRanking b) {
        List<TimeSlot> aWorkstations = timeSlots.getAllWithRanking(a) ;
        List<TimeSlot> bWorkstations = timeSlots.getAllWithRanking(b) ;
        return new FollowsConGenerator(aWorkstations, bWorkstations).getConstraints();
    }

    private List<ConstraintIF> getPerShiftCons(ErgonomicRanking ranking, int maxCount) {
        List<TimeSlot> workstations = timeSlots.getAllWithRanking(ranking) ;
        Set<String> workstationUserIds = prb.getWorkerIdsOnWorkstations(workstations, prb.getWorkerWorkstationLevel()) ;
        return new PerShiftConGenerator(workstations,workstationUserIds, maxCount).getConstraints() ;
    }

}
