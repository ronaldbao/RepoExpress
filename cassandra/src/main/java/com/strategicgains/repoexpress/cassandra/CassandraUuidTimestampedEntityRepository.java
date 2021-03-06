package com.strategicgains.repoexpress.cassandra;

import com.datastax.driver.core.Session;
import com.strategicgains.repoexpress.domain.TimestampedIdentifiable;
import com.strategicgains.repoexpress.domain.UuidIdentifiable;
import com.strategicgains.repoexpress.event.DefaultTimestampedIdentifiableRepositoryObserver;
import com.strategicgains.repoexpress.event.UuidIdentityRepositoryObserver;

/**
 * A Cassandra repository that manages types of Timestamped UuidIdentifiable instances, which are
 * identified by a single UUID primary key. It utilizes the {@link UuidIdentityRepositoryObserver}
 * to assign a UUID on creation.  It also uses {@link DefaultTimestampedIdentifiableRepositoryObserver}
 * to set the createAt and updatedAt dates on the object as appropriate.
 *
 * <p/>
 * Extend this repository to persist entities identified by a UUID and implement Timestamped, so need the
 * createdAt and updatedAt time stamps automatically applied.
 * 
 * @author toddf
 * @since Jan 28, 2014
 */
public abstract class CassandraUuidTimestampedEntityRepository<T extends UuidIdentifiable & TimestampedIdentifiable>
extends CassandraUuidEntityRepository<T>
{
	public CassandraUuidTimestampedEntityRepository(Session session, String tableName, String identifierColumn)
    {
	    super(session, tableName, identifierColumn);
    }

	@Override
    protected void initializeObservers()
    {
		super.initializeObservers();
		addObserver(new DefaultTimestampedIdentifiableRepositoryObserver<T>());
    }
}
