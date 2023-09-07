package com.aifunny.profile

import com.aifunny.model.profile.CreateProfileRequest
import com.aifunny.model.profile.ProfileResponse
import org.springframework.stereotype.Service

interface ProfileService {
    fun createProfile(profile: CreateProfileRequest): ProfileResponse
    fun getProfile(profileId: String): ProfileResponse?
    fun updateProfile(profileId: String, profile: CreateProfileRequest): ProfileResponse?
}

@Service
class ProfileServiceImpl : ProfileService {

    override fun createProfile(profile: CreateProfileRequest): ProfileResponse {
        TODO("Not yet implemented")
    }

    override fun getProfile(profileId: String): ProfileResponse? {
        TODO("Not yet implemented")
    }

    override fun updateProfile(profileId: String, profile: CreateProfileRequest): ProfileResponse? {
        TODO("Not yet implemented")
    }

}