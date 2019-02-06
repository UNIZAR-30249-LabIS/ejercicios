package unizar.labis;

import java.util.UUID;

public interface Entity {
	default UUID getId() {
		return UUID.randomUUID();
	}
}
