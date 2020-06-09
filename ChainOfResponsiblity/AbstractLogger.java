package ChainOfResponsiblity;

public abstract class AbstractLogger {
   public static int BOOK = 1;
   public static int BOOKAUTHOR = 2; 
   public static int STAFF = 3; 
public static int ORDER = 4; 

   protected int level;
   protected AbstractLogger nextLogger;
   public void setNextLogger(AbstractLogger nextLogger){
      this.nextLogger = nextLogger;
   }

   public void logMessage(int level,Object obj ){ 
      if(this.level== level){
       
         Add(obj);
      }
      if(nextLogger !=null){
         nextLogger.logMessage(level,obj);
      }
   }

   abstract protected void Add(Object obj );
	
}