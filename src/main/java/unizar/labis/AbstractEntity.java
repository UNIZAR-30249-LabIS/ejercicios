package unizar.labis;

import java.util.UUID;

public class AbstractEntity implements Entity {
	private UUID id;

	public AbstractEntity() {
		id = UUID.randomUUID();
	}

	@Override
	public UUID getId() {
		return id;
	}
}
