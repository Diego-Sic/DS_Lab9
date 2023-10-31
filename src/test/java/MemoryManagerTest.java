import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemoryManagerTest {
	MemoryAllocation mem1;
	MemoryAllocation mem2;
	MemoryAllocation mem3;
	MemoryAllocation mem4;
	MemoryAllocation mem5;
	MemoryAllocation mem6;
	MemoryAllocation memNew;
	
	MemoryManager manager;
	@BeforeEach
	void setUp() throws Exception {
		manager = new MemoryManager(100);
		
	}

	@Test
	void test() {
		mem1 = manager.requestMemory(10, "mem1");
		assertEquals(0, mem1.getPosition());
		assertEquals(10, mem1.getLength());
		assertEquals("mem1", mem1.getOwner());
		mem2 = manager.requestMemory(10, "mem2");
		assertEquals(10, mem2.getPosition());
		assertEquals(10, mem2.getLength());
		assertEquals("mem2", mem2.getOwner());
		mem3 = manager.requestMemory(15, "mem3");
		assertEquals(20, mem3.getPosition());
		assertEquals(15, mem3.getLength());
		assertEquals("mem3", mem3.getOwner());
		mem4 = manager.requestMemory(25, "mem4");
		assertEquals(35, mem4.getPosition());
		assertEquals(25, mem4.getLength());
		assertEquals("mem4", mem4.getOwner());
		mem5 = manager.requestMemory(25, "mem5");
		assertEquals(60, mem5.getPosition());
		assertEquals(25, mem5.getLength());
		assertEquals("mem5", mem5.getOwner());
		mem6 = manager.requestMemory(15, "mem6");
		assertEquals(85, mem6.getPosition());
		assertEquals(15, mem6.getLength());
		assertEquals("mem6", mem6.getOwner());
		
		assertEquals(null, manager.requestMemory(10, "BAD"));
		
		manager.returnMemory(mem2);
		memNew = manager.requestMemory(10, "Filler");
		assertEquals(10, memNew.getPosition());
		assertEquals(10, memNew.getLength());
		assertEquals("Filler", memNew.getOwner());
		manager.returnMemory(memNew);
		
		manager.returnMemory(mem4);
		manager.returnMemory(mem6);
		
		memNew = manager.requestMemory(15, "Filler");
		assertEquals(35, memNew.getPosition());
		assertEquals(15, memNew.getLength());
		assertEquals("Filler", memNew.getOwner());
		
		manager.returnMemory(mem3);
		manager.returnMemory(memNew);
		mem2 = manager.requestMemory(30, "mem2");
		assertEquals(10, mem2.getPosition());
		assertEquals(30, mem2.getLength());
		assertEquals("mem2", mem2.getOwner());
		
		manager.returnMemory(mem2);
		memNew = manager.requestMemory(45, "Filler");
		assertEquals(10, memNew.getPosition());
		assertEquals(45, memNew.getLength());
		assertEquals("Filler", memNew.getOwner());
	}
}
		