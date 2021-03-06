SUMMARY = "OpenBMC for OpenPOWER - Applications"
PR = "r1"

inherit packagegroup
inherit obmc-phosphor-license

PROVIDES = "${PACKAGES}"
PACKAGES = " \
        ${PN}-chassis \
        ${PN}-fans \
        ${PN}-flash \
        ${PN}-system \
        "

PROVIDES += "virtual/obmc-chassis-mgmt"
PROVIDES += "virtual/obmc-fan-mgmt"
PROVIDES += "virtual/obmc-flash-mgmt"
PROVIDES += "virtual/obmc-system-mgmt"

RPROVIDES_${PN}-chassis += "virtual-obmc-chassis-mgmt"
RPROVIDES_${PN}-fans += "virtual-obmc-fan-mgmt"
RPROVIDES_${PN}-flash += "virtual-obmc-flash-mgmt"
RPROVIDES_${PN}-system += "virtual-obmc-system-mgmt"

SUMMARY_${PN}-chassis = "OpenPOWER Chassis"
RDEPENDS_${PN}-chassis = " \
        obmc-button-power \
        obmc-button-reset \
        obmc-control-chassis \
        obmc-op-control-power \
        obmc-pcie-detect \
        obmc-host-failure-reboots \
        "
#Pull in obmc-fsi on all P9 OpenPOWER systems
RDEPENDS_${PN}-chassis += "${@mf_enabled(d, 'op-fsi', 'op-fsi')}"

#Pull in p9-cfam-override on all P9 OpenPOWER systems
RDEPENDS_${PN}-chassis += "${@mf_enabled(d, 'p9-cfam-override', 'p9-cfam-override')}"

SUMMARY_${PN}-fans = "OpenPOWER Fans"
RDEPENDS_${PN}-fans = " \
        obmc-control-fan \
        "

SUMMARY_${PN}-flash = "OpenPOWER Flash"
RDEPENDS_${PN}-flash = " \
        obmc-control-bmc \
        ${@mf_enabled(d, 'openpower-ubi-fs', \
            'openpower-software-manager', \
            'obmc-flash-bios obmc-mgr-download obmc-op-flasher')} \
        "

SUMMARY_${PN}-system = "OpenPOWER System"
RDEPENDS_${PN}-system = " \
        obmc-mgr-system \
        pdbg \
        "
