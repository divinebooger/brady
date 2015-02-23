package synthesizer;
public class ArrayRingBuffer extends AbstractBoundedQueue {
  /* Index for the next dequeue or peek. */
  private int first;           
  /* Index for the next enqueue. */
  private int last;             
  /* Array for storing the buffer data. */
  private double[] rb;

  /** Create a new ArrayRingBuffer with the given capacity. */
  public ArrayRingBuffer(int capacity) {
    // TODO: Create new array with capacity elements.
    //       first, last, and fillCount should all be set to 0. 
    //       this.capacity should be set appropriately. Note that the local variable
    //       here shadows the field we inherit from AbstractBoundedQueue.
    this.capacity = capacity;
    first = 0;
    last = 0;
    fillCount = 0;
    rb = new double[capacity];
  }

  /** Adds x to the end of the ring buffer. If there is no room, then
    * throw new RuntimeException("Ring buffer overflow") 
    */
  @Override
  public void enqueue(double x) {
    // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    // is there room?
    if ( !isFull() ) {
      rb[last] = x;
      last += 1;
      fillCount += 1;
      if (last == capacity) last = 0;
    }
    else{
      throw new RuntimeException("Ring buffer overflow");
    }
  }

  /** Dequeue oldest item in the ring buffer. If the buffer is empty, then
    * throw new RuntimeException("Ring buffer underflow");
    */
  public double dequeue() {
    // TODO: Dequeue the first item. Don't forget to decrease fillCount and update first.
    if ( !isEmpty() ){
      double temp = first;
      first += 1;
      fillCount -= 1;
      if (first == capacity) first = 0;
      return temp;
    }
    throw new RuntimeException("Ring buffer underflow");
  }

  /** Return oldest item, but don't remove it. */
  public double peek() {
    // TODO: Return the first item. None of your instance variables should change.
   if ( isEmpty() ) throw new RuntimeException("Ring buffer underflow");
   else return rb[first];
  }
}
