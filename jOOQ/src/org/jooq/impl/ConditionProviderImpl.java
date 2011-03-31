/**
 * Copyright (c) 2009-2011, Lukas Eder, lukas.eder@gmail.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * . Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * . Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * . Neither the name of the "jOOQ" nor the names of its contributors may be
 *   used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.jooq.impl;

import static org.jooq.impl.TrueCondition.TRUE_CONDITION;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import org.jooq.Comparator;
import org.jooq.Condition;
import org.jooq.ConditionProvider;
import org.jooq.Configuration;
import org.jooq.Field;
import org.jooq.Operator;
import org.jooq.Select;

/**
 * @author Lukas Eder
 */
class ConditionProviderImpl extends AbstractQueryPart implements ConditionProvider, Condition {

    private static final long serialVersionUID = 6073328960551062973L;

    private Condition         condition;

    ConditionProviderImpl(Configuration configuration) {
        super(configuration);
    }

    final Condition getWhere() {
        if (condition == null) {
            return TRUE_CONDITION;
        }

        return condition;
    }

    // -------------------------------------------------------------------------
    // ConditionProvider API
    // -------------------------------------------------------------------------

    @Override
    public final void addConditions(Condition... conditions) {
        addConditions(Operator.AND, conditions);
    }

    @Override
    public final void addConditions(Collection<Condition> conditions) {
        addConditions(Operator.AND, conditions);
    }

    @Override
    public final void addConditions(Operator operator, Condition... conditions) {
        addConditions(operator, Arrays.asList(conditions));
    }

    @SuppressWarnings("deprecation")
    @Override
    public final void addConditions(Operator operator, Collection<Condition> conditions) {
        if (!conditions.isEmpty()) {
            Condition c;

            if (conditions.size() == 1) {
                c = conditions.iterator().next();
            }
            else {
                c = create().combinedCondition(operator, conditions);
            }

            if (getWhere() == TRUE_CONDITION) {
                condition = c;
            }
            else {
                condition = create().combinedCondition(operator, getWhere(), c);
            }
        }
    }

    @Override
    @Deprecated
    public final <T> void addBetweenCondition(Field<T> field, T minValue, T maxValue) {
        addConditions(create().betweenCondition(field, minValue, maxValue));
    }

    @Override
    @Deprecated
    public final <T> void addCompareCondition(Field<T> field, T value, Comparator comparator) {
        addConditions(create().compareCondition(field, value, comparator));
    }

    @Override
    @Deprecated
    public final <T> void addCompareCondition(Field<T> field, T value) {
        addConditions(create().compareCondition(field, value));
    }

    @Override
    @Deprecated
    public final void addNullCondition(Field<?> field) {
        addConditions(create().nullCondition(field));
    }

    @Override
    @Deprecated
    public final void addNotNullCondition(Field<?> field) {
        addConditions(create().notNullCondition(field));
    }

    @Override
    @Deprecated
    public final <T> void addInCondition(Field<T> field, Collection<T> values) {
        addConditions(create().inCondition(field, values));
    }

    @Override
    @Deprecated
    public final <T> void addInCondition(Field<T> field, T... values) {
        addConditions(create().inCondition(field, values));
    }

    @Override
    public final int bind(Configuration configuration, PreparedStatement stmt, int initialIndex) throws SQLException {
        return getWhere().getQueryPart().bind(configuration, stmt, initialIndex);
    }

    @Override
    public final String toSQLReference(Configuration configuration, boolean inlineParameters) {
        return getWhere().getQueryPart().toSQLReference(configuration, inlineParameters);
    }

    // -------------------------------------------------------------------------
    // Condition API
    // -------------------------------------------------------------------------

    @Override
    public final Condition and(Condition other) {
        return getWhere().and(other);
    }

    @Override
    public final Condition and(String sql) {
        return getWhere().and(sql);
    }

    @Override
    public final Condition and(String sql, Object... bindings) {
        return getWhere().and(sql, bindings);
    }

    @Override
    public final Condition andNot(Condition other) {
        return getWhere().andNot(other);
    }

    @Override
    public final Condition andExists(Select<?> select) {
        return getWhere().andExists(select);
    }

    @Override
    public final Condition andNotExists(Select<?> select) {
        return getWhere().andNotExists(select);
    }

    @Override
    public final Condition or(Condition other) {
        return getWhere().or(other);
    }

    @Override
    public final Condition or(String sql) {
        return getWhere().or(sql);
    }

    @Override
    public final Condition or(String sql, Object... bindings) {
        return getWhere().or(sql, bindings);
    }

    @Override
    public final Condition orNot(Condition other) {
        return getWhere().orNot(other);
    }

    @Override
    public final Condition orExists(Select<?> select) {
        return getWhere().orExists(select);
    }

    @Override
    public final Condition orNotExists(Select<?> select) {
        return getWhere().orNotExists(select);
    }

    @Override
    public final Condition not() {
        return getWhere().not();
    }
}
