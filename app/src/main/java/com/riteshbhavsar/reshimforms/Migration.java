package com.riteshbhavsar.reshimforms;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by ritesh.bhavsar on 10-07-2017.
 */

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        // During a migration, a DynamicRealm is exposed. A DynamicRealm is an untyped variant of a normal Realm, but
        // with the same object creation and query capabilities.
        // A DynamicRealm uses Strings instead of Class references because the Classes might not even exist or have been
        // renamed.

        // Access the Realm schema in order to create, modify or delete classes and their fields.
        RealmSchema schema = realm.getSchema();
        //version 1
        //added
//        private String birthPlace;
//        private String expectation;
//        private String fathersFullName;
//        private String fathersOccupation;
//        private String fullNameOfMama;
//        private String raas;
//        private String nakshatra;

        // Migrate from version 0 to version 1
        if (oldVersion == 0) {
            RealmObjectSchema personSchema = schema.get("Person");

            // Combine 'firstName' and 'lastName' in a new field called 'fullName'
            personSchema
                    .addField("fullname", String.class)
                    .addField("birthPlace", String.class)
                    .addField("expectation", String.class)
                    .addField("fathersFullName", String.class)
                    .addField("fathersOccupation", String.class)
                    .addField("fullNameOfMama", String.class)
                    .addField("raas", String.class)
                    .addField("nakshatra", String.class)

                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("fullName", obj.getString("firstName") + " " + obj.getString("middleName")
                                    + " " + obj.getString("lastName"));
                        }
                    })
                    .removeField("firstName")
                    .removeField("middleName")
                    .removeField("lastName");
            oldVersion++;
        }
    }
}
