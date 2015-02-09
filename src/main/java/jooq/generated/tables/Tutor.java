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
public class Tutor extends org.jooq.impl.TableImpl<jooq.generated.tables.records.TutorRecord> {

	private static final long serialVersionUID = 1429580243;

	/**
	 * The reference instance of <code>tutorme.tutor</code>
	 */
	public static final jooq.generated.tables.Tutor TUTOR = new jooq.generated.tables.Tutor();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<jooq.generated.tables.records.TutorRecord> getRecordType() {
		return jooq.generated.tables.records.TutorRecord.class;
	}

	/**
	 * The column <code>tutorme.tutor.compID</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.TutorRecord, java.lang.String> COMPID = createField("compID", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>tutorme.tutor.year</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.TutorRecord, java.lang.Integer> YEAR = createField("year", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>tutorme.tutor.bio</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.TutorRecord, java.lang.String> BIO = createField("bio", org.jooq.impl.SQLDataType.CLOB.length(65535), this, "");

	/**
	 * The column <code>tutorme.tutor.gpa</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.TutorRecord, java.math.BigDecimal> GPA = createField("gpa", org.jooq.impl.SQLDataType.DECIMAL.precision(4, 3), this, "");

	/**
	 * The column <code>tutorme.tutor.isActive</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.TutorRecord, java.lang.Integer> ISACTIVE = createField("isActive", org.jooq.impl.SQLDataType.INTEGER.defaulted(true), this, "");

	/**
	 * The column <code>tutorme.tutor.hourlyRate</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.TutorRecord, java.math.BigDecimal> HOURLYRATE = createField("hourlyRate", org.jooq.impl.SQLDataType.DECIMAL.precision(5, 2).defaulted(true), this, "");

	/**
	 * The column <code>tutorme.tutor.photoURL</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.TutorRecord, java.lang.String> PHOTOURL = createField("photoURL", org.jooq.impl.SQLDataType.VARCHAR.length(200).defaulted(true), this, "");

	/**
	 * The column <code>tutorme.tutor.major</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.TutorRecord, java.lang.String> MAJOR = createField("major", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * Create a <code>tutorme.tutor</code> table reference
	 */
	public Tutor() {
		this("tutor", null);
	}

	/**
	 * Create an aliased <code>tutorme.tutor</code> table reference
	 */
	public Tutor(java.lang.String alias) {
		this(alias, jooq.generated.tables.Tutor.TUTOR);
	}

	private Tutor(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.TutorRecord> aliased) {
		this(alias, aliased, null);
	}

	private Tutor(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.TutorRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, jooq.generated.Tutorme.TUTORME, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<jooq.generated.tables.records.TutorRecord> getPrimaryKey() {
		return jooq.generated.Keys.KEY_TUTOR_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<jooq.generated.tables.records.TutorRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<jooq.generated.tables.records.TutorRecord>>asList(jooq.generated.Keys.KEY_TUTOR_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<jooq.generated.tables.records.TutorRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<jooq.generated.tables.records.TutorRecord, ?>>asList(jooq.generated.Keys.TUTOR_IBFK_1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public jooq.generated.tables.Tutor as(java.lang.String alias) {
		return new jooq.generated.tables.Tutor(alias, this);
	}

	/**
	 * Rename this table
	 */
	public jooq.generated.tables.Tutor rename(java.lang.String name) {
		return new jooq.generated.tables.Tutor(name, null);
	}
}