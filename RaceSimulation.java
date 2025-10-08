package two;


class Racer extends Thread {  

  private String name;  

  private int sleepTime;  



  public Racer(String name, int sleepTime) {  

      this.name = name;  

      this.sleepTime = sleepTime;  

  }     public void run() {  

      for (int step = 1; step <= 5; step++) {  

          System.out.println(name + " - Step " + step);  

try {  

          Thread.sleep(sleepTime);  

          }  

      catch (InterruptedException e) {  

                  e.printStackTrace();  

          }  

      }  



System.out.println(name + " has finished the race!");  

  }  

}  



public class RaceSimulation {  

  public static void main(String[] args) {  

      Racer threadA = new Racer("Thread A", 500);  

      Racer threadB = new Racer("Thread B", 700);  

      Racer threadC = new Racer("Thread C", 500);  



      threadA.start();  

      threadB.start();  

      threadC.start(); 



try {  

          threadA.join();  

          threadB.join();  

          threadC.join();  

      }  

catch (InterruptedException e) {  

          e.printStackTrace();  

      }  


      System.out.println();  


  }  

} 