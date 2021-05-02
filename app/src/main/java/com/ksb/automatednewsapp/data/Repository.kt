package com.ksb.automatednewsapp.data

import com.ksb.automatednewsapp.data.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(val remoteDataSource: RemoteDataSource) {
}