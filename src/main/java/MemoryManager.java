public class MemoryManager {
    private MemoryAllocation head;
    private MemoryAllocation tail;
    private final long size;

    public MemoryManager(long size) {
        this.size = size;
        head = new MemoryAllocation("head", 0, 0, null);
        tail = new MemoryAllocation("tail", size, 0, null);
        head.next = tail;
    }

    public MemoryAllocation requestMemory(long requestedSize, String requester) {
        MemoryAllocation prev = head;
        MemoryAllocation current = head.next;

        while (current != null) {
            long gap = current.pos - (prev.pos + prev.len);
            if (gap >= requestedSize) {
                MemoryAllocation newMemory = new MemoryAllocation(requester, requestedSize, prev.pos + prev.len, current);
                prev.next = newMemory;
                return newMemory;
            }
            prev = current;
            current = current.next;
        }
        return null; 
        
    }

    public void returnMemory(MemoryAllocation memory) {
        MemoryAllocation prev = head;
        MemoryAllocation current = head.next;

        while (current != null) {
            if (current == memory) {
                prev.next = current.next;
                break;
            }
            prev = current;
            current = current.next;
        }
    }
}
