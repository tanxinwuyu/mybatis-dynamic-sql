/**
 *    Copyright 2016-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package examples.simple;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.mybatis.dynamic.sql.SqlConditions.*;

import java.sql.JDBCType;
import java.util.Date;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.delete.render.DeleteSupport;
import org.mybatis.dynamic.sql.insert.render.InsertSupport;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.SelectModelBuilder.SelectSupportAfterFromBuilder;
import org.mybatis.dynamic.sql.select.render.SelectSupport;
import org.mybatis.dynamic.sql.update.UpdateModelBuilder;
import org.mybatis.dynamic.sql.update.render.UpdateSupport;

public interface SimpleTableDynamicSqlSupport {
    SqlTable simpleTable = SqlTable.of("SimpleTable").withAlias("a");
    SqlColumn<Integer> id = SqlColumn.of("id", JDBCType.INTEGER).inTable(simpleTable).withAlias("A_ID");
    SqlColumn<String> firstName = SqlColumn.of("first_name", JDBCType.VARCHAR).inTable(simpleTable);
    SqlColumn<String> lastName = SqlColumn.of("last_name", JDBCType.VARCHAR).inTable(simpleTable);
    SqlColumn<Date> birthDate = SqlColumn.of("birth_date", JDBCType.DATE).inTable(simpleTable);
    SqlColumn<Boolean> employed = SqlColumn.of("employed", JDBCType.VARCHAR).withTypeHandler("examples.simple.YesNoTypeHandler").inTable(simpleTable);
    SqlColumn<String> occupation = SqlColumn.of("occupation", JDBCType.VARCHAR).inTable(simpleTable);
    
    static InsertSupport<SimpleTableRecord> buildFullInsertSupport(SimpleTableRecord record) {
        return insert(record)
                .into(simpleTable)
                .map(id).toProperty("id")
                .map(firstName).toProperty("firstName")
                .map(lastName).toProperty("lastName")
                .map(birthDate).toProperty("birthDate")
                .map(employed).toProperty("employed")
                .map(occupation).toProperty("occupation")
                .build()
                .render(RenderingStrategy.MYBATIS3);
    }

    static InsertSupport<SimpleTableRecord> buildSelectiveInsertSupport(SimpleTableRecord record) {
        return insert(record)
                .into(simpleTable)
                .map(id).toPropertyWhenPresent("id")
                .map(firstName).toPropertyWhenPresent("firstName")
                .map(lastName).toPropertyWhenPresent("lastName")
                .map(birthDate).toPropertyWhenPresent("birthDate")
                .map(employed).toPropertyWhenPresent("employed")
                .map(occupation).toPropertyWhenPresent("occupation")
                .build()
                .render(RenderingStrategy.MYBATIS3);
    }
    
    static UpdateSupport buildFullUpdateByPrimaryKeySupport(SimpleTableRecord record) {
        return update(simpleTable)
                .set(firstName).equalTo(record.getFirstName())
                .set(lastName).equalTo(record.getLastName())
                .set(birthDate).equalTo(record.getBirthDate())
                .set(employed).equalTo(record.getEmployed())
                .set(occupation).equalTo(record.getOccupation())
                .where(id, isEqualTo(record.getId()))
                .build()
                .render(RenderingStrategy.MYBATIS3);
    }

    static UpdateSupport buildSelectiveUpdateByPrimaryKeySupport(SimpleTableRecord record) {
        return update(simpleTable)
                .set(firstName).equalToWhenPresent(record.getFirstName())
                .set(lastName).equalToWhenPresent(record.getLastName())
                .set(birthDate).equalToWhenPresent(record.getBirthDate())
                .set(employed).equalToWhenPresent(record.getEmployed())
                .set(occupation).equalToWhenPresent(record.getOccupation())
                .where(id, isEqualTo(record.getId()))
                .build()
                .render(RenderingStrategy.MYBATIS3);
    }

    static UpdateModelBuilder updateByExample(SimpleTableRecord record) {
        return update(simpleTable)
                .set(id).equalTo(record.getId())
                .set(firstName).equalTo(record.getFirstName())
                .set(lastName).equalTo(record.getLastName())
                .set(birthDate).equalTo(record.getBirthDate())
                .set(employed).equalTo(record.getEmployed())
                .set(occupation).equalTo(record.getOccupation());
    }

    static UpdateModelBuilder updateByExampleSelective(SimpleTableRecord record) {
        return update(simpleTable)
                .set(id).equalToWhenPresent(record.getId())
                .set(firstName).equalToWhenPresent(record.getFirstName())
                .set(lastName).equalToWhenPresent(record.getLastName())
                .set(birthDate).equalToWhenPresent(record.getBirthDate())
                .set(employed).equalToWhenPresent(record.getEmployed())
                .set(occupation).equalToWhenPresent(record.getOccupation());
    }

    static DeleteSupport buildDeleteByPrimaryKeySupport(Integer id_) {
        return deleteFrom(simpleTable)
                .where(id, isEqualTo(id_))
                .build()
                .render(RenderingStrategy.MYBATIS3);
    }
    
    static SelectSupportAfterFromBuilder selectByExample() {
        return select(id, firstName, lastName, birthDate, employed, occupation)
            .from(simpleTable);
    }

    static SelectSupport buildSelectByPrimaryKeySupport(Integer id_) {
        return select(id, firstName, lastName, birthDate, employed, occupation)
            .from(simpleTable)
            .where(id, isEqualTo(id_))
            .build()
            .render(RenderingStrategy.MYBATIS3);
    }
}
