/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.orm.jpa.vendor;

import java.sql.Connection;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.batoo.jpa.core.impl.manager.EntityManagerImpl;
import org.springframework.jdbc.datasource.ConnectionHandle;
import org.springframework.jdbc.datasource.SimpleConnectionHandle;
import org.springframework.orm.jpa.DefaultJpaDialect;

/**
 * {@link org.springframework.orm.jpa.JpaDialect} implementation for Batoo JPA.
 * Developed and tested against BatooJPA 2.0.0.0.
 *
 * @author Hasan Ceylan
 * @since 3.2.0
 * @see org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
 */
public class BatooJpaDialect extends DefaultJpaDialect {

	@Override
	public ConnectionHandle getJdbcConnection(EntityManager em, boolean readOnly)
			throws PersistenceException, SQLException {
		EntityManagerImpl emImpl = (EntityManagerImpl) em;
		
		Connection con = emImpl.getConnection();
		return (con != null ? new SimpleConnectionHandle(con) : null);
	}
}
