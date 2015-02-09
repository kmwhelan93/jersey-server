/**
 * This class is generated by jOOQ
 */
package jooq.generated.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Student extends org.jooq.impl.TableImpl<jooq.generated.tables.records.StudentRecord> {

	private static final long serialVersionUID = -1229964412;

	/**
	 * The reference instance of <code>tutorme.student</code>
	 */
	public static final jooq.generated.tables.Student STUDENT = new jooq.generated.tables.Student();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<jooq.generated.tables.records.StudentRecord> getRecordType() {
		return jooq.generated.tables.records.StudentRecord.class;
	}

	/**
	 * The column <code>tutorme.student.compID</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.StudentRecord, java.lang.String> COMPID = createField("compID", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>tutorme.student.firstName</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.StudentRecord, java.lang.String> FIRSTNAME = createField("firstName", org.jooq.impl.SQLDataType.VARCHAR.length(75), this, "");

	/**
	 * The column <code>tutorme.student.lastName</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.StudentRecord, java.lang.String> LASTNAME = createField("lastName", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

	/**
	 * The column <code>tutorme.student.email</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.StudentRecord, java.lang.String> EMAIL = createField("email", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

	/**
	 * Create a <code>tutorme.student</code> table reference
	 */
	public Student() {
		this("student", null);
	}

	/**
	 * Create an aliased <code>tutorme.student</code> table reference
	 */
	public Student(java.lang.String alias) {
		this(alias, jooq.generated.tables.Student.STUDENT);
	}

	private Student(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.StudentRecord> aliased) {
		this(alias, aliased, null);
	}

	private Student(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.StudentRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, jooq.generated.Tutorme.TUTORME, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<jooq.generated.tables.records.StudentRecord> getPrimaryKey() {
		return jooq.generated.Keys.KEY_STUDENT_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<jooq.generated.tables.records.StudentRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<jooq.generated.tables.records.StudentRecord>>asList(jooq.generated.Keys.KEY_STUDENT_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public jooq.generated.tables.Student as(java.lang.String alias) {
		return new jooq.generated.tables.Student(alias, this);
	}

	/**
	 * Rename this table
	 */
	public jooq.generated.tables.Student rename(java.lang.String name) {
		return new jooq.generated.tables.Student(name, null);
	}
}