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
public class Ratecourses extends org.jooq.impl.TableImpl<jooq.generated.tables.records.RatecoursesRecord> {

	private static final long serialVersionUID = -1908363576;

	/**
	 * The reference instance of <code>tutorme.rateCourses</code>
	 */
	public static final jooq.generated.tables.Ratecourses RATECOURSES = new jooq.generated.tables.Ratecourses();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<jooq.generated.tables.records.RatecoursesRecord> getRecordType() {
		return jooq.generated.tables.records.RatecoursesRecord.class;
	}

	/**
	 * The column <code>tutorme.rateCourses.raterID</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.RatecoursesRecord, java.lang.String> RATERID = createField("raterID", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>tutorme.rateCourses.tutorID</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.RatecoursesRecord, java.lang.String> TUTORID = createField("tutorID", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>tutorme.rateCourses.courseID</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.RatecoursesRecord, java.lang.String> COURSEID = createField("courseID", org.jooq.impl.SQLDataType.VARCHAR.length(15).nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>tutorme.rateCourses</code> table reference
	 */
	public Ratecourses() {
		this("rateCourses", null);
	}

	/**
	 * Create an aliased <code>tutorme.rateCourses</code> table reference
	 */
	public Ratecourses(java.lang.String alias) {
		this(alias, jooq.generated.tables.Ratecourses.RATECOURSES);
	}

	private Ratecourses(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.RatecoursesRecord> aliased) {
		this(alias, aliased, null);
	}

	private Ratecourses(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.RatecoursesRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, jooq.generated.Tutorme.TUTORME, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<jooq.generated.tables.records.RatecoursesRecord> getPrimaryKey() {
		return jooq.generated.Keys.KEY_RATECOURSES_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<jooq.generated.tables.records.RatecoursesRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<jooq.generated.tables.records.RatecoursesRecord>>asList(jooq.generated.Keys.KEY_RATECOURSES_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<jooq.generated.tables.records.RatecoursesRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<jooq.generated.tables.records.RatecoursesRecord, ?>>asList(jooq.generated.Keys.RATECOURSES_IBFK_5, jooq.generated.Keys.RATECOURSES_IBFK_6);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public jooq.generated.tables.Ratecourses as(java.lang.String alias) {
		return new jooq.generated.tables.Ratecourses(alias, this);
	}

	/**
	 * Rename this table
	 */
	public jooq.generated.tables.Ratecourses rename(java.lang.String name) {
		return new jooq.generated.tables.Ratecourses(name, null);
	}
}
