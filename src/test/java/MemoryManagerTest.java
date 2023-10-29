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
		assertTrue(comparator(chunk1, correct1));
		
		MemoryAllocation chunk2 = Memory.requestMemory(size2, "Mehak");
		assertTrue(comparator(chunk2, correct2));
		
		assertEquals(null, Memory.requestMemory(size3, "None"));
		
		MemoryAllocation chunk4 = Memory.requestMemory(size4, "Davis");
		assertTrue(comparator(chunk4, correct4));
		
		assertEquals(null, Memory.requestMemory(size1, "None"));
		
		Memory.returnMemory(chunk1);
		
		MemoryAllocation newchunk1 =  Memory.requestMemory(size1, "NewChunk");
		newCorrect1 = new MemoryAllocation(NewChunk, pos1, size1, null, correct2);
		assertTrue(comparator(newchunk1, newCorrect1));
	}
	
	boolean comparator(MemoryAllocation mem1, MemoryAllocation mem2) {
		if(! mem1.getOwner().equals(mem2.getOwner())) {
			return false;
		}
		if(mem1.getPosition() != mem2.getPosition()) {
			return false;
		}
		if(mem1.getLength() != mem2.getLength()){
			return false;
		}
		return true;
	}

}
