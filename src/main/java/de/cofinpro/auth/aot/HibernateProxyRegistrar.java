package de.cofinpro.auth.aot;

import org.hibernate.query.CommonQueryContract;
import org.hibernate.query.SelectionQuery;
import org.hibernate.query.hql.spi.SqmQueryImplementor;
import org.hibernate.query.spi.DomainQueryExecutionContext;
import org.hibernate.query.sqm.internal.SqmInterpretationsKey;
import org.springframework.aot.hint.ProxyHints;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.lang.NonNull;

public class HibernateProxyRegistrar implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(@NonNull RuntimeHints hints, ClassLoader classLoader) {
        ProxyHints proxies = hints.proxies();
        proxies.registerJdkProxy(
                SqmQueryImplementor.class,
                SqmInterpretationsKey.InterpretationsKeySource.class,
                DomainQueryExecutionContext.class,
                SelectionQuery.class,
                CommonQueryContract.class
                );
    }
}