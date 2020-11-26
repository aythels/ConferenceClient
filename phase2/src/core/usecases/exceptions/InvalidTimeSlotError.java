package core.usecases.exceptions;

public class InvalidTimeSlotError extends Error {

   public InvalidTimeSlotError(String msg) {
       super(msg);
   }

   public InvalidTimeSlotError() {
       super();
   }
}
