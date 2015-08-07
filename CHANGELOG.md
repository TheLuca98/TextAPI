###TextAPI 2.0 Changelog
* TextAPI has been re-licensed under the GNU Lesser General Public License.
* `ActionBar` is now an actual class. It now works similarly to `Title`.
  * As a consequence, the static utility methods have been removed from it.
* Both `Title` and `ActionBar` now also support JSON for formatting text.
  * Please note that normal text is still supported.
* Access to CraftBukkit and Minecraft Server classes is now done with Reflection.
  * As a consequence, CraftBukkit is no longer required as a dependency and has been removed from the POM.
  * This will also improve compatibility with Minecraft updates.
* New version naming rules: `{Minecraft Version}-{TextAPI Version}`
* Added a `DEBUG` option, both for `Title` and `ActionBar`.
* Added a couple of internal utilities. They are not part of the API and are unsupported.
* The official Spigot Maven repository has been added to the POM.