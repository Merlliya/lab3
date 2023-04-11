package org.example;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Comparator;


public class GenMultPercetron {
        java.util.List<Double> proba;
        java.util.List<ChromoPerceptron> chromosomes;
        static int POPULATION_SIZE = 1000;  
        GenMultPercetron(){
            init();
            for(int i=0; i<1000; i++){
                mark();
                selection();
                //selectionRulet();
                recombination();
                
            }
            mark();
            selection();
            //selectionRulet();
            ChromoPerceptron best = chromosomes.get(0);
            //System.out.println(best.weights[0]+" "+best.weights[1]+" "+best.fitness);
            best.test();
        }

        void init(){
            chromosomes = new java.util.ArrayList<>();
            proba = new java.util.ArrayList<>();
            java.util.Random r = new java.util.Random();
            for(int i=0; i<100; i++){
                ChromoPerceptron chromosome = new ChromoPerceptron();
                chromosome.weights[0] = r.nextDouble();
                chromosome.weights[1] = r.nextDouble();
                chromosomes.add(chromosome);
            }
        }

        void mark(){
            for(ChromoPerceptron chromosome:chromosomes){
                chromosome.countFitness();
            }
        }

        void selection(){
            chromosomes.sort(new Comparator(){
                @Override
                public int compare(Object obj1, Object obj2){
                    int res = 0;
                    double fitness1 = ((ChromoPerceptron)obj1).fitness;
                    double fitness2 = ((ChromoPerceptron)obj2).fitness;
                    if (fitness2<fitness1) res=1; else
                    if(fitness2>fitness1) res = -1; else res=0;
                    return res;
                }
            });
            
            
            chromosomes = chromosomes.subList(0, chromosomes.size()/2);
            for(ChromoPerceptron chromosome:chromosomes){
                System.out.println(chromosome.weights[0]+" "+chromosome.weights[1]+" "+chromosome.fitness);;
            }
            System.out.println("__________________________________________________________");
        }

    void selection1() {
        chromosomes.sort(new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                int res = 0;
                double prob1 = ((Chromosome) obj1).selectionProb;
                double prob2 = ((Chromosome) obj2).selectionProb;
                if (prob2 < prob1) res = 1;
                else if (prob2 > prob1) res = -1;
                else res = 0;
                return res;
            }
        });
    }

//        void selectionRulet() {
//            double prob = 0;
//            double max = 0;
//            java.util.ArrayList<Chromosome> chromosomes2 = new java.util.ArrayList<>();
//            java.util.Random r = new java.util.Random();
//            for (int i = 0; i < chromosomes.size(); i++) {
//                prob += chromosomes.get(i).selectionProb;
//                proba.add(prob);
//            }
//            for (int i = 0; i < chromosomes.size(); i++) {
//                max = max < chromosomes.get(i).selectionProb?chromosomes.get(i).selectionProb:max;
//            }
//
//            for(int i=0; i<50; i++){
//                int index = -1;
//                double subject = r.nextDouble(max);
//
//                /*for (int j = 0; j < proba.size(); j++) {
//                    index = proba.get(j) >= subject ? j : -1;
//                    if (index >= 0) {
//                        chromosomes2.add(chromosomes.get(index));
//                        break;
//                    }
//                }*/
//                for (int j = 0; j < chromosomes.size(); j++) {
//                    index = chromosomes.get(j).selectionProb >= subject ? j : -1;
//                    if (index >= 0) {
//                        chromosomes2.add(chromosomes.get(index));
//                        break;
//                    }
//                }
//            }
//            chromosomes=chromosomes2;
//            proba.clear();
//        }


        void recombination(){
            java.util.Random r = new java.util.Random();
            java.util.ArrayList<ChromoPerceptron> chromosomes2 = new java.util.ArrayList<>();
            for(int i=0; i<50; i++){
                int index = r.nextInt(chromosomes.size());
                ChromoPerceptron c1 = chromosomes.get(index);

                index = r.nextInt(chromosomes.size());
                ChromoPerceptron c2 = chromosomes.get(index);


                ChromoPerceptron c1new = new ChromoPerceptron();
                c1new.weights[0] = c1.weights[0];
                c1new.weights[1] = c2.weights[1];

                ChromoPerceptron c2new = new ChromoPerceptron();
                c2new.weights[0] = c2.weights[0];
                c2new.weights[1] = c1.weights[1];

                chromosomes2.add(c1new);
                chromosomes2.add(c2new);
            }
            chromosomes = chromosomes2;
        }
}
