/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * def
 */

package com.microsoft.azure.management.network.v2019_09_01.implementation;

import com.microsoft.azure.arm.resources.collection.implementation.GroupableResourcesCoreImpl;
import com.microsoft.azure.management.network.v2019_09_01.VpnServerConfigurations;
import com.microsoft.azure.management.network.v2019_09_01.VpnServerConfiguration;
import rx.Observable;
import rx.Completable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import com.microsoft.azure.arm.resources.ResourceUtilsCore;
import com.microsoft.azure.arm.utils.RXMapper;
import rx.functions.Func1;
import com.microsoft.azure.PagedList;
import com.microsoft.azure.Page;

class VpnServerConfigurationsImpl extends GroupableResourcesCoreImpl<VpnServerConfiguration, VpnServerConfigurationImpl, VpnServerConfigurationInner, VpnServerConfigurationsInner, NetworkManager>  implements VpnServerConfigurations {
    protected VpnServerConfigurationsImpl(NetworkManager manager) {
        super(manager.inner().vpnServerConfigurations(), manager);
    }

    @Override
    protected Observable<VpnServerConfigurationInner> getInnerAsync(String resourceGroupName, String name) {
        VpnServerConfigurationsInner client = this.inner();
        return client.getByResourceGroupAsync(resourceGroupName, name);
    }

    @Override
    protected Completable deleteInnerAsync(String resourceGroupName, String name) {
        VpnServerConfigurationsInner client = this.inner();
        return client.deleteAsync(resourceGroupName, name).toCompletable();
    }

    @Override
    public Observable<String> deleteByIdsAsync(Collection<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return Observable.empty();
        }
        Collection<Observable<String>> observables = new ArrayList<>();
        for (String id : ids) {
            final String resourceGroupName = ResourceUtilsCore.groupFromResourceId(id);
            final String name = ResourceUtilsCore.nameFromResourceId(id);
            Observable<String> o = RXMapper.map(this.inner().deleteAsync(resourceGroupName, name), id);
            observables.add(o);
        }
        return Observable.mergeDelayError(observables);
    }

    @Override
    public Observable<String> deleteByIdsAsync(String...ids) {
        return this.deleteByIdsAsync(new ArrayList<String>(Arrays.asList(ids)));
    }

    @Override
    public void deleteByIds(Collection<String> ids) {
        if (ids != null && !ids.isEmpty()) {
            this.deleteByIdsAsync(ids).toBlocking().last();
        }
    }

    @Override
    public void deleteByIds(String...ids) {
        this.deleteByIds(new ArrayList<String>(Arrays.asList(ids)));
    }

    @Override
    public PagedList<VpnServerConfiguration> listByResourceGroup(String resourceGroupName) {
        VpnServerConfigurationsInner client = this.inner();
        return this.wrapList(client.listByResourceGroup(resourceGroupName));
    }

    @Override
    public Observable<VpnServerConfiguration> listByResourceGroupAsync(String resourceGroupName) {
        VpnServerConfigurationsInner client = this.inner();
        return client.listByResourceGroupAsync(resourceGroupName)
        .flatMapIterable(new Func1<Page<VpnServerConfigurationInner>, Iterable<VpnServerConfigurationInner>>() {
            @Override
            public Iterable<VpnServerConfigurationInner> call(Page<VpnServerConfigurationInner> page) {
                return page.items();
            }
        })
        .map(new Func1<VpnServerConfigurationInner, VpnServerConfiguration>() {
            @Override
            public VpnServerConfiguration call(VpnServerConfigurationInner inner) {
                return wrapModel(inner);
            }
        });
    }

    @Override
    public PagedList<VpnServerConfiguration> list() {
        VpnServerConfigurationsInner client = this.inner();
        return this.wrapList(client.list());
    }

    @Override
    public Observable<VpnServerConfiguration> listAsync() {
        VpnServerConfigurationsInner client = this.inner();
        return client.listAsync()
        .flatMapIterable(new Func1<Page<VpnServerConfigurationInner>, Iterable<VpnServerConfigurationInner>>() {
            @Override
            public Iterable<VpnServerConfigurationInner> call(Page<VpnServerConfigurationInner> page) {
                return page.items();
            }
        })
        .map(new Func1<VpnServerConfigurationInner, VpnServerConfiguration>() {
            @Override
            public VpnServerConfiguration call(VpnServerConfigurationInner inner) {
                return wrapModel(inner);
            }
        });
    }

    @Override
    public VpnServerConfigurationImpl define(String name) {
        return wrapModel(name);
    }

    @Override
    protected VpnServerConfigurationImpl wrapModel(VpnServerConfigurationInner inner) {
        return  new VpnServerConfigurationImpl(inner.name(), inner, manager());
    }

    @Override
    protected VpnServerConfigurationImpl wrapModel(String name) {
        return new VpnServerConfigurationImpl(name, new VpnServerConfigurationInner(), this.manager());
    }

}