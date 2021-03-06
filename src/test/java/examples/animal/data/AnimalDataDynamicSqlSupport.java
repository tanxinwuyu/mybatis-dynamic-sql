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
package examples.animal.data;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public interface AnimalDataDynamicSqlSupport {
    SqlTable animalData = SqlTable.of("AnimalData").withAlias("a");
    SqlColumn<Integer> id = SqlColumn.of("id", JDBCType.INTEGER).inTable(animalData); 
    SqlColumn<String> animalName = SqlColumn.of("animal_name", JDBCType.VARCHAR).inTable(animalData);
    SqlColumn<Double> bodyWeight = SqlColumn.of("body_weight", JDBCType.DOUBLE).inTable(animalData);
    SqlColumn<Double> brainWeight = SqlColumn.of("brain_weight", JDBCType.DOUBLE).inTable(animalData);
}
