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
public class Rates extends org.jooq.impl.TableImpl<jooq.generated.tables.records.RatesRecord> {

	private static final long serialVersionUID = 1273785159;

	/**
	 * The reference instance of <code>tutorme.rates</code>
	 */
	public static final jooq.generated.tables.Rates RATES = new jooq.generated.tables.Rates();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<jooq.generated.tables.records.RatesRecord> getRecordType() {
		return jooq.generated.tables.records.RatesRecord.class;
	}

	/**
	 * The column <code>tutorme.rates.raterID</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.RatesRecord, java.lang.String> RATERID = createField("raterID", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>tutorme.rates.tutorID</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.RatesRecord, java.lang.String> TUTORID = createField("tutorID", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>tutorme.rates.knowledge</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.RatesRecord, java.lang.Integer> KNOWLEDGE = createField("knowledge", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>tutorme.rates.clarity</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.RatesRecord, java.lang.Integer> CLARITY = createField("clarity", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>tutorme.rates.helpfulness</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.RatesRecord, java.lang.Integer> HELPFULNESS = createField("helpfulness", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>tutorme.rates.hottness</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.RatesRecord, java.lang.Integer> HOTTNESS = createField("hottness", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>tutorme.rates.time</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.RatesRecord, java.sql.Timestamp> TIME = createField("time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>tutorme.rates.review</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.RatesRecord, java.lang.String> REVIEW = createField("review", org.jooq.impl.SQLDataType.CLOB.length(65535), this, "");

	/**
	 * Create a <code>tutorme.rates</code> table reference
	 */
	public Rates() {
		this("rates", null);
	}

	/**
	 * Create an aliased <code>tutorme.rates</code> table reference
	 */
	public Rates(java.lang.String alias) {
		this(alias, jooq.generated.tables.Rates.RATES);
	}

	private Rates(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.RatesRecord> aliased) {
		this(alias, aliased, null);
	}

	private Rates(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.RatesRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, jooq.generated.Tutorme.TUTORME, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<jooq.generated.tables.records.RatesRecord> getPrimaryKey() {
		return jooq.generated.Keys.KEY_RATES_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<jooq.generated.tables.records.RatesRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<jooq.generated.tables.records.RatesRecord>>asList(jooq.generated.Keys.KEY_RATES_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<jooq.generated.tables.records.RatesRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<jooq.generated.tables.records.RatesRecord, ?>>asList(jooq.generated.Keys.RATES_IBFK_1, jooq.generated.Keys.RATES_IBFK_2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public jooq.generated.tables.Rates as(java.lang.String alias) {
		return new jooq.generated.tables.Rates(alias, this);
	}

	/**
	 * Rename this table
	 */
	public jooq.generated.tables.Rates rename(java.lang.String name) {
		return new jooq.generated.tables.Rates(name, null);
	}
}