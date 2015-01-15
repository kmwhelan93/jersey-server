/**
 * This class is generated by jOOQ
 */
package jooq.generated.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Reinforcements extends org.jooq.impl.TableImpl<jooq.generated.tables.records.ReinforcementsRecord> {

	private static final long serialVersionUID = 764001234;

	/**
	 * The singleton instance of <code>cs4720cem6at.reinforcements</code>
	 */
	public static final jooq.generated.tables.Reinforcements REINFORCEMENTS = new jooq.generated.tables.Reinforcements();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<jooq.generated.tables.records.ReinforcementsRecord> getRecordType() {
		return jooq.generated.tables.records.ReinforcementsRecord.class;
	}

	/**
	 * The column <code>cs4720cem6at.reinforcements.src_portal_id</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.ReinforcementsRecord, java.lang.Integer> SRC_PORTAL_ID = createField("src_portal_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>cs4720cem6at.reinforcements.dest_portal_id</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.ReinforcementsRecord, java.lang.Integer> DEST_PORTAL_ID = createField("dest_portal_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>cs4720cem6at.reinforcements.units_remaining</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.ReinforcementsRecord, java.lang.Integer> UNITS_REMAINING = createField("units_remaining", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>cs4720cem6at.reinforcements.init_time</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.ReinforcementsRecord, java.sql.Timestamp> INIT_TIME = createField("init_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>cs4720cem6at.reinforcements</code> table reference
	 */
	public Reinforcements() {
		this("reinforcements", null);
	}

	/**
	 * Create an aliased <code>cs4720cem6at.reinforcements</code> table reference
	 */
	public Reinforcements(java.lang.String alias) {
		this(alias, jooq.generated.tables.Reinforcements.REINFORCEMENTS);
	}

	private Reinforcements(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.ReinforcementsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Reinforcements(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.ReinforcementsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, jooq.generated.Cs4720cem6at.CS4720CEM6AT, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<jooq.generated.tables.records.ReinforcementsRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<jooq.generated.tables.records.ReinforcementsRecord, ?>>asList(jooq.generated.Keys.REINFORCEMENTS_IBFK_1, jooq.generated.Keys.REINFORCEMENTS_IBFK_2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public jooq.generated.tables.Reinforcements as(java.lang.String alias) {
		return new jooq.generated.tables.Reinforcements(alias, this);
	}

	/**
	 * Rename this table
	 */
	public jooq.generated.tables.Reinforcements rename(java.lang.String name) {
		return new jooq.generated.tables.Reinforcements(name, null);
	}
}
