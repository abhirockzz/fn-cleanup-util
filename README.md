# Functions clean up utility

Cascade deletion....

- Triggers
- Routes
- Functions
- Apps

## run... 

- `git clone https://github.com/abhirockzz/fn-cleanup-util.git`
- `cd fn-cleanup-util`
- update `util.properties`

		root_tenancy_ocid=
		public_key_fingerprint=
		user_ocid=
		private_key_file_path=
		compartment_id=

- `mvn clean install`
- `java -jar target/fn-util-1.0.jar util.properties`
