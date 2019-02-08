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

	@Override
	// Iguales si sus id son iguales
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Entity e = (Entity) o;
		return e.getId().equals(this.getId());
	}
}
