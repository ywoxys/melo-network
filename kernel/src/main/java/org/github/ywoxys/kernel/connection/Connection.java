package org.github.ywoxys.kernel.connection;

import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.plugin.Plugin;
import org.github.ywoxys.kernel.base.Base;

import java.util.logging.Logger;

@Getter @Setter
public class Connection extends Plugin {

    @Getter @Setter
    public static Connection instance;

    @Override
    public void onLoad() {
        setInstance(this);

        Base.setLogger(getLogger());
        Base.setPlatform(new ConnectionPlataform());

        //fazer o serverinfo carregar

    }
}
