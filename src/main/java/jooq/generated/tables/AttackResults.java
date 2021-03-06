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
public class AttackResults extends org.jooq.impl.TableImpl<jooq.generated.tables.records.AttackResultsRecord> {

	private static final long serialVersionUID = 572620568;

	/**
	 * The reference instance of <code>maimon.attack_results</code>
	 */
	public static final jooq.generated.tables.AttackResults ATTACK_RESULTS = new jooq.generated.tables.AttackResults();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<jooq.generated.tables.records.AttackResultsRecord> getRecordType() {
		return jooq.generated.tables.records.AttackResultsRecord.class;
	}

	/**
	 * The column <code>maimon.attack_results.attack_id</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.AttackResultsRecord, java.lang.Integer> ATTACK_ID = createField("attack_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>maimon.attack_results.winner_username</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.AttackResultsRecord, java.lang.String> WINNER_USERNAME = createField("winner_username", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>maimon.attack_results.num_units_left</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.AttackResultsRecord, java.lang.Integer> NUM_UNITS_LEFT = createField("num_units_left", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>maimon.attack_results.new_base_id</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.AttackResultsRecord, java.lang.Integer> NEW_BASE_ID = createField("new_base_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>maimon.attack_results.attacker_has_viewed</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.AttackResultsRecord, java.lang.Byte> ATTACKER_HAS_VIEWED = createField("attacker_has_viewed", org.jooq.impl.SQLDataType.TINYINT.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>maimon.attack_results.defender_has_viewed</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.AttackResultsRecord, java.lang.Byte> DEFENDER_HAS_VIEWED = createField("defender_has_viewed", org.jooq.impl.SQLDataType.TINYINT.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>maimon.attack_results</code> table reference
	 */
	public AttackResults() {
		this("attack_results", null);
	}

	/**
	 * Create an aliased <code>maimon.attack_results</code> table reference
	 */
	public AttackResults(java.lang.String alias) {
		this(alias, jooq.generated.tables.AttackResults.ATTACK_RESULTS);
	}

	private AttackResults(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.AttackResultsRecord> aliased) {
		this(alias, aliased, null);
	}

	private AttackResults(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.AttackResultsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, jooq.generated.Maimon.MAIMON, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<jooq.generated.tables.records.AttackResultsRecord> getPrimaryKey() {
		return jooq.generated.Keys.KEY_ATTACK_RESULTS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<jooq.generated.tables.records.AttackResultsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<jooq.generated.tables.records.AttackResultsRecord>>asList(jooq.generated.Keys.KEY_ATTACK_RESULTS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public jooq.generated.tables.AttackResults as(java.lang.String alias) {
		return new jooq.generated.tables.AttackResults(alias, this);
	}

	/**
	 * Rename this table
	 */
	public jooq.generated.tables.AttackResults rename(java.lang.String name) {
		return new jooq.generated.tables.AttackResults(name, null);
	}
}
