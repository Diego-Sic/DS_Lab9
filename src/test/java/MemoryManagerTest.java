import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemoryManagerTest {
	
	MemoryManager Memory;
	long size = 200;
	long size1 = 20;
	long size2 = 30;
	long size3 = 200;
	long size4 = 150;
	long pos1 = 0;
	long pos2 = 20;
	long pos3 = 50;
	String Diego = "Diego";
	String Mehak = "Mehak";
	String Davis = "Davis";
	String NewChunk = "NewChunk";
	MemoryAllocation correct1;
	MemoryAllocation correct2;
	MemoryAllocation correct4;
	MemoryAllocation newCorrect1;
	
	@BeforeEach
	void setup() {
		Memory = new MemoryManager(size);
		correct1 = new MemoryAllocation(Diego, pos1, size1, null, correct2);
		correct2 = new MemoryAllocation(Mehak, pos2, size2, correct1, correct4);
		correct4 = new MemoryAllocation(Davis, pos3, size4, correct2, null);
	}
	@Test
	void test() {
		
		MemoryAllocation chunk1 = Memory.requestMemory(size1, "Diego");
		
		assertEquals(chunk1.getOwner(), correct1.getOwner());
		assertEquals(chunk1.getPosition(), correct1.getPosition());
		assertEquals(chunk1.getLength(), correct1.getLength());
		
		MemoryAllocation chunk2 = Memory.requestMemory(size2, "Mehak");
		assertEquals(correct2.getOwner(), chunk2.getOwner());
		assertEquals(chunk2.getPosition(), correct2.getPosition());
		assertEquals(chunk2.getLength(), correct2.getLength());
		
		assertEquals(null, Memory.requestMemory(size3, "None"));
		
		MemoryAllocation chunk4 = Memory.requestMemory(size4, "Davis");
		assertEquals(correct4.getOwner(), chunk4.getOwner());
		assertEquals(chunk4.getPosition(), correct4.getPosition());
		assertEquals(chunk4.getLength(), correct4.getLength());
		
		assertEquals(null, Memory.requestMemory(size1, "None"));
		
		Memory.returnMemory(chunk1);
		
		MemoryAllocation newchunk1 =  Memory.requestMemory(size1, "NewChunk");
		newCorrect1 = new MemoryAllocation(NewChunk, pos1, size1, null, correct2);
        assertEquals(newCorrect1.getOwner(), newchunk1.getOwner());
        assertEquals(newCorrect1.getPosition(), newchunk1.getPosition());
        assertEquals(newCorrect1.getLength(), newchunk1.getLength());

		
		
	}

}
