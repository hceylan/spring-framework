/*
 * Copyright 2002-2011 the original author or authors.
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

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.spi.PersistenceProvider;

import org.batoo.jpa.BJPASettings;
import org.batoo.jpa.core.BatooEntityManager;
import org.batoo.jpa.core.impl.jdbc.PreparedStatementProxy.SqlLoggingType;
import org.batoo.jpa.core.jdbc.DDLMode;
import org.springframework.orm.jpa.JpaDialect;

/**
 * {@link org.springframework.orm.jpa.JpaVendorAdapter} implementation for Batoo JPA. Developed 
 * and tested against BatooJPA 2.0.0.0.
 *
 * <p>Exposes BatooJPA's persistence provider and EntityManager extension interface,
 * and supports {@link AbstractJpaVendorAdapter}'s common configuration settings.
 *
 * @author Hasan Ceylan
 * @since 3.2.0
 * @see org.batoo.jpa.core.BatooPersistenceProvider
 * @see org.batoo.jpa.core.impl.manager.EntityManagerImpl
 */
public class BatooJpaVendorAdapter extends AbstractJpaVendorAdapter {

	private final PersistenceProvider persistenceProvider = new org.batoo.jpa.core.BatooPersistenceProvider();

	private final JpaDialect jpaDialect = new BatooJpaDialect();


	public PersistenceProvider getPersistenceProvider() {
		return this.persistenceProvider;
	}

	@Override
	public Map<String, Object> getJpaPropertyMap() {
		Map<String, Object> jpaProperties = new HashMap<String, Object>();

		if (isGenerateDdl()) {
			jpaProperties.put(BJPASettings.DDL,
					DDLMode.CREATE.name());
		}
		if (isShowSql()) {
			jpaProperties.put(BJPASettings.SQL_LOGGING, SqlLoggingType.STDOUT.name());
		}

		return jpaProperties;
	}

	@Override
	public JpaDialect getJpaDialect() {
		return this.jpaDialect;
	}

	@Override
	public Class<? extends EntityManager> getEntityManagerInterface() {
		return BatooEntityManager.class;
	}
}
